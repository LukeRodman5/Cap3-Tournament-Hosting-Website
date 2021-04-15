<template>
<div id="bracket-generator">
    <div id="set-bracket-area" v-if="isHost">
        <h1>Generate Tournament Helper</h1>
        <p>Hello! As host, use the below form to setup the tournament.</p>
        <div id="rounds-form">
            <div v-for="round in eachRoundSegment" v-bind:key="round.id">
                <h3>{{ round === eachRoundSegment[eachRoundSegment.length - 1] ? 'Finals' : 'Round' }}</h3>
                <div class="round-setup" v-for="match in round" v-bind:key="match.matchId">
                    <h3>Set Match:</h3>
                    <select v-model="match.player1Name">
                        <option value=""></option>
                        <option v-for="user in usersInTourney" v-bind:key="user.userId" v-bind:value="user.username">{{user.username}}</option>
                    </select>
                    <select v-model="match.player2Name">
                        <option value=""></option>
                        <option v-for="user in usersInTourney" v-bind:key="user.userId" v-bind:value="user.username">{{user.username}}</option>
                    </select>
                    <div id="choose-winner">
                        <p>Select Winner:</p>
                        <label><input type="radio" v-model="match.playerWin" value="1" v-bind:name="match.matchId" />Player 1</label>
                        <label><input type="radio" v-model="match.playerWin" value="2" v-bind:name="match.matchId" />Player 2</label>
                        <label><input type="radio" v-model="match.playerWin" value="3" v-bind:name="match.matchId" />None</label>
                    </div>
                </div>
            </div>
        </div>
        <input type="button" id="generate-button"
            v-bind:value="'Generate Tournament'"
            v-on:click.prevent="checkDelete()"
        />
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
            eachRoundSegment: [],
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
                    let player1InMatch = currentUsersInMatch.find((link) => {
                        return link.playerNum == 1
                    })
                    let player2InMatch = currentUsersInMatch.find((link) => {
                        return link.playerNum == 2
                    })
                    
                    let player1Info = this.usersInTourney.find((user) => {
                        return player1InMatch.userId === user.userId
                    })
                    let player2Info = this.usersInTourney.find((user) => {
                        return player2InMatch.userId === user.userId
                    })

                    currentMatch.player1Name = player1Info.username
                    currentMatch.player2Name = player2Info.username

                    if (!player1InMatch.winStatus && !player2InMatch.winStatus) {
                        currentMatch.playerWin = 3
                        player1InMatch.winStatus = null
                        player2InMatch.winStatus = null
                    } else if (player1InMatch.winStatus) {
                        currentMatch.playerWin = 1
                    } else {
                        currentMatch.playerWin = 2
                    }

                    player1 = {id: player1Info.userId, name: currentMatch.player1Name, winner: player1InMatch.winStatus}
                    player2 = {id: player2Info.userId, name: currentMatch.player2Name, winner: player2InMatch.winStatus}

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

            // if (player1Info != undefined) {
            //     applicationServices.addUserToMatch(currentMatch.matchId, player1Info.username, 1)
            // }
            // if (player2Info != undefined) {
            //     applicationServices.addUserToMatch(currentMatch.matchId, player2Info.username, 2)
            // }
            // if (currentMatch.playerWin == 1) {
            //     applicationServices.updateWinStatus(true, player1Info.userId, currentMatch.matchId)
            //     applicationServices.updateWinStatus(false, player2Info.userId, currentMatch.matchId)
            // } else if (currentMatch.playerWIn == 2) {
            //     applicationServices.updateWinStatus(false, player1Info.userId, currentMatch.matchId)
            //     applicationServices.updateWinStatus(true, player2Info.userId, currentMatch.matchId)
            // } else {
            //     applicationServices.updateWinStatus(false, player1Info.userId, currentMatch.matchId)
            //     applicationServices.updateWinStatus(false, player2Info.userId, currentMatch.matchId)
            // }
            // this.getUserMatchLink()

            if (player1Info != undefined) {
                applicationServices.addUserToMatch(currentMatch.matchId, player1Info.username, 1).then((response) => {
                    if (response.status >= 200 && response.status < 300) {
                        if (player2Info != undefined) {
                            applicationServices.addUserToMatch(currentMatch.matchId, player2Info.username, 2).then((response) => {
                                if (response.status >= 200 && response.status < 300) {
                                    if (currentMatch.playerWin == 1) {
                                        applicationServices.updateWinStatus(true, player1Info.userId, currentMatch.matchId)
                                        applicationServices.updateWinStatus(false, player2Info.userId, currentMatch.matchId)
                                    } else if (currentMatch.playerWin == 2) {
                                        applicationServices.updateWinStatus(false, player1Info.userId, currentMatch.matchId)
                                        applicationServices.updateWinStatus(true, player2Info.userId, currentMatch.matchId)
                                    } else {
                                        applicationServices.updateWinStatus(false, player1Info.userId, currentMatch.matchId)
                                        applicationServices.updateWinStatus(false, player2Info.userId, currentMatch.matchId)
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
        },
        seperatedRounds() {
            let maxRound = this.matchesInTourney[this.matchesInTourney.length - 1].roundLevel
            for (let i = 0; i <= maxRound; i++) {
                let roundArray = this.matchesInTourney.filter((match) => {
                    return match.roundLevel === i
                })
                this.eachRoundSegment.push(roundArray)
            }
        },
        checkResponse() {
            applicationServices.getUserMatchLink(this.currentTourney.tourneyId).then((response) => {
                if (response.status >= 200 && response.status < 300) {
                    this.seperatedRounds()
                    this.createBracket()
                }
            })
        }
    },
    created() {
        this.isHost = this.currentTourney.tourneyHost === this.$store.state.user.username,
        this.getUserMatchLink(),
        this.checkResponse()
    }
}
</script>

<style scoped>
#rounds-form {
    display: flex;
    flex-direction: row;
    justify-content: center;
    gap: 30px;
}
.round-setup {
    align-items: center;
}
#generate-button {
    margin: 20px auto 20px auto;
}
#bracket {
    display: flex;
    justify-content: center;
    padding-bottom: 50px;
}
.bracket-name {
    color: white;
}
</style>