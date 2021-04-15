<template>
<div id="bracket-generator">
    <div id="set-bracket-area" v-if="isHost">
        <h1>Generate Tournament Helper</h1>
        <p>Hello! As host, use the below form to setup the tournament.</p>
        <div id="rounds-form">
            <div v-for="match in matchesInTourney" v-bind:key="match.matchId">
                <h3>Set Match:</h3>
                <select v-model="match.player1Name">
                    <option v-for="user in usersInTourney" v-bind:key="user.userId" v-bind:value="user.username">{{user.username}}</option>
                </select>
                <select v-model="match.player2Name">
                    <option v-for="user in usersInTourney" v-bind:key="user.userId" v-bind:value="user.username">{{user.username}}</option>
                </select>
                <input type="radio" v-model="match.playerWin" value="1" />
                <label>Switch Winners</label>
                <!-- <input type="radio" v-model="match.playerWin" value="2" />
                <input type="radio" v-model="match.playerWin" value="3" /> -->
            </div>
            <input type="button" id="generate-button"
                v-bind:value="'Generate Tournament'"
                v-on:click.prevent="checkDelete()"
            />
        </div>
    </div>
    <div id="bracket">
      <bracket :rounds="rounds">
        <template slot="player" slot-scope="{ player }">
            <div class="bracket-name">{{ player.name }}</div>
        </template>
      </bracket>
    </div>
</div>
</template>

<script>
import applicationServices from '@/services/ApplicationServices.js'
import Bracket from "vue-tournament-bracket"

const tbdPlayers = { name: "TBD", winner: null }

export default {
    name: 'generate-tournament',
    props: ["currentTourney", "usersInTourney", "matchesInTourney"],
    components: {
        Bracket
    },
    data() {
        return {
            isHost: false,
            userMatchLink: [],
            rounds: []
        }
    },
    methods: {
        createBracket() {
            this.rounds = []
            let gamesHolder = {games: []}
            let match = {}
            
            let amountMatchesAdded = 0
            let matchNeededPerRound = this.currentTourney.maxNumOfParticipants / 2

            for (let i = 0; i < this.matchesInTourney.length; i++) {
                let currentMatch = this.matchesInTourney[i]
                let player1 = tbdPlayers
                let player2 = tbdPlayers

                let currentUsersInMatch = this.userMatchLink.filter((link) => {
                    return link.matchId === currentMatch.matchId
                })

                if (currentUsersInMatch.length > 0) {
                    // if (currentMatch.playerWin == 1) {
                    //     player1 = {name: currentMatch.player1Name, winner: true}
                    //     player2 = {name: currentMatch.player2Name, winner: false}
                    // } else if (currentMatch.playerWin == 2) {
                    //     player1 = {name: currentMatch.player1Name, winner: false}
                    //     player2 = {name: currentMatch.player2Name, winner: true}
                    // } else {
                    //     player1 = {name: currentMatch.player1Name, winner: null}
                    //     player2 = {name: currentMatch.player2Name, winner: null}
                    // }
                    let player1Info = this.usersInTourney.find((user) => {
                        return user.userId === currentUsersInMatch[0].userId
                    })
                    let player2Info = this.usersInTourney.find((user) => {
                        return user.userId === currentUsersInMatch[1].userId
                    })
                    currentMatch.player1Name = player1Info.username
                    currentMatch.player2Name = player2Info.username
                    currentMatch.winnerStatus
                    
                    player1 = {name: currentMatch.player1Name, winner: currentUsersInMatch[0].winStatus}
                    player2 = {name: currentMatch.player2Name, winner: currentUsersInMatch[1].winStatus}

                    match = {player1: player1, player2: player2}
                } else {
                    match = {player1: player1, player2: player2}
                }
                
                gamesHolder.games.push(match)
                match = {}
                amountMatchesAdded++

                if (amountMatchesAdded === matchNeededPerRound) {
                    this.rounds.push(gamesHolder)
                    gamesHolder = {games: []}
                    amountMatchesAdded = 0
                    matchNeededPerRound /= 2
                }
            }
        },
        checkDelete() {
           for (let i = 0; i < this.matchesInTourney.length; i++) {
                let currentMatch = this.matchesInTourney[i]
                let currentUsersInMatch = this.userMatchLink.filter((link) => {
                    return link.matchId === currentMatch.matchId
                })

                let count = 0;
                let countNeeded = currentUsersInMatch.length
                if (countNeeded > 0) {
                    currentUsersInMatch.forEach((link) => {
                        let linkedUser = this.usersInTourney.find((user) => {
                            return user.userId === link.userId
                        })
                        applicationServices.deleteUserFromMatch(currentMatch.matchId, linkedUser.username).then((response) => {
                            if (response.status >= 200 && response.status < 300) {
                                count++
                                if (count === countNeeded) {
                                    this.addToDb(currentMatch)
                                }
                            }
                        })
                    })
                } else {
                    this.addToDb(currentMatch)
                }
           } 
        },
        addToDb(currentMatch) {
            let player1Info = this.usersInTourney.find((user) => {
                return user.username === currentMatch.player1Name
            })
            let player2Info = this.usersInTourney.find((user) => {
                return user.username === currentMatch.player2Name
            })
            if (player1Info != undefined) {
                applicationServices.addUserToMatch(currentMatch.matchId, player1Info.username).then((response) => {
                    if (response.status >= 200 && response.status < 300) {
                        if (player2Info != undefined) {
                            applicationServices.addUserToMatch(currentMatch.matchId, player2Info.username).then((response) => {
                                if (response.status >= 200 && response.status < 300) {
                                    if (currentMatch.playerWin == 1) {
                                        applicationServices.updateWinStatus(true, player1Info.userId, currentMatch.matchId)
                                        applicationServices.updateWinStatus(false, player2Info.userId, currentMatch.matchId)
                                    } else if (currentMatch.playerWIn == 2) {
                                        applicationServices.updateWinStatus(false, player1Info.userId, currentMatch.matchId)
                                        applicationServices.updateWinStatus(true, player2Info.userId, currentMatch.matchId)
                                    }
                                    this.getUserMatchLink()
                                }
                            })
                        }
                    }
                })
            }
        },
        getUserMatchLink() {
            applicationServices.getUserMatchLink(this.currentTourney.tourneyId).then((response) => {
                if (response.status >= 200 && response.status < 300) {
                    this.userMatchLink = response.data
                    this.createBracket()
                }
            })
        }
    },
    created() {
        this.isHost = this.currentTourney.tourneyHost === this.$store.state.user.username,
        this.getUserMatchLink()
    }
}
</script>

<style scoped>
#rounds-form {
    display: flex;
    flex-direction: row;
    gap: 30px;
}
#bracket {
    display: flex;
    justify-content: center;
}
#generate-button {
    margin-top: 15px;
    margin-bottom: 30px;
}
.bracket-name {
    color: white;
}
</style>