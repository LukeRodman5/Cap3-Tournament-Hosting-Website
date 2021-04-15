<template>
<div id="bracket-generator">
    <div id="set-bracket-area"  v-if="!matchesInDb && isHost">
        <h1>Generate Tournament Helper</h1>
        <p>Hello! As host, use the below form to setup the tournament.</p>
        <div v-for="match in initialMatches" v-bind:key="match.matchId">
        <h3>Set Match:</h3>
        <select v-model="match.player1">
            <option v-for="user in usersInTourney" v-bind:key="user.userId" v-bind:value="user.username">{{user.username}}</option>
        </select>
        <select v-model="match.player2">
            <option v-for="user in usersInTourney" v-bind:key="user.userId" v-bind:value="user.username">{{user.username}}</option>
        </select>
        </div>
        <input type="button" id="generate-button"
            v-bind:value="(usersInTourney.length !== initialMatches.length * 2) ? 'Not enough users!' : 'Generate Tournament'"
            v-bind:disabled="usersInTourney.length !== initialMatches.length * 2" 
            v-on:click.prevent="addToDatabase()"
        />
    </div>
    <div id="bracket">
      <bracket :rounds="rounds">
        <template slot="player" slot-scope="{ player }">
            <div class="bracket-name" v-on:click="changeToWin(player)">{{ player.name }}</div>
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
            matchesInDb: false,
            userMatchLink: [],
            rounds: []
        }
    },
    methods: {
        addToDatabase() {
            this.initialMatches.forEach((iniMatch) => {
                applicationServices.addUserToMatch(iniMatch.matchId, iniMatch.player1).then((response) => {
                    if (response.status >= 200 && response.status < 300) {
                        applicationServices.addUserToMatch(iniMatch.matchId, iniMatch.player2).then((response) => {
                            if (response.status >= 200 && response.status < 300) {
                                this.matchesInDb = true
                                this.getUserMatchLink()
                            }
                        })
                    }
                })
            })
        },
        setBracketData() {
            // values needed for third-party bracket createor
            this.rounds = []
            let gamesHolder = {games: []}
            let match = {}

            // helps keep track of data location
            let matchesAdded = 0
            let totalMatchesNeeded = this.matchesInTourney.length
            let maxNumOfParticipants = this.currentTourney.maxNumOfParticipants
            let matchesNeededPerRound = maxNumOfParticipants / 2
           

            for (let i = 0; i < totalMatchesNeeded; i++) {
                let currentMatch = this.matchesInTourney[i]
                let usersInMatch = this.userMatchLink.filter((link) => {
                    return link.matchId === currentMatch.matchId
                })

                // Adds pre-setup users as initial matches with no win color
                if (currentMatch.roundLevel === 0) {
                    let player1 = this.usersInTourney.find((user) => {
                        return user.userId === usersInMatch[0].userId
                    })
                    let player2 = this.usersInTourney.find((user) => {
                        return user.userId === usersInMatch[1].userId
                    })

                    if (!usersInMatch[0].winStatus && !usersInMatch[1].winStatus) {
                        usersInMatch[0].winStatus = null
                        usersInMatch[1].winStatus = null
                    }

                    match.player1 = {id: player1.userId, name: player1.username, winner: usersInMatch[0].winStatus}
                    match.player2 = {id: player2.userId, name: player2.username, winner: usersInMatch[1].winStatus}

                    gamesHolder.games.push(match)
                    match = {}
                    matchesAdded++
                // dyanically change followup brackets depending on win status of a user
                } else {
                    let prevRoundMatches = this.matchesInTourney.filter((match) => {
                        return match.roundLevel === currentMatch.roundLevel - 1
                    })

                    let currRoundMatches = this.matchesInTourney.filter((match) => {
                        return match.roundLevel === currentMatch.roundLevel
                    })

                    let firstMatchToCheck = currRoundMatches.indexOf(currentMatch) * 2

                    let usersPrevMatch1 = this.userMatchLink.filter((link) => {
                        return prevRoundMatches[firstMatchToCheck].matchId === link.matchId
                    })

                    let usersPrevMatch2 = this.userMatchLink.filter((link) => {
                        return prevRoundMatches[firstMatchToCheck + 1].matchId === link.matchId
                    })

                    let doesBothMatchesHaveWin = (usersPrevMatch1[0].winStatus ||
                                                  usersPrevMatch1[1].winStatus) &&
                                                  (usersPrevMatch2[0].winStatus ||
                                                  usersPrevMatch2[1].winStatus)
                    if (!doesBothMatchesHaveWin) {
                        match = {player1: tbdPlayers, player2: tbdPlayers}
                    } else {
                        this.updateFollowUpMatch(currentMatch.matchId, usersPrevMatch1, usersPrevMatch2)
                        applicationServices.getUserMatchLink(this.currentTourney.tourneyId)
                        usersInMatch = this.userMatchLink.filter((link) => {
                            return link.matchId === currentMatch.matchId
                        })

                        let player1 = this.usersInTourney.find((user) => {
                                return user.userId === usersInMatch[0].userId
                            })
                        let player2 = this.usersInTourney.find((user) => {
                            return user.userId === usersInMatch[1].userId
                        })

                        if (!usersInMatch[0].winStatus && !usersInMatch[1].winStatus) {
                            usersInMatch[0].winStatus = null
                            usersInMatch[1].winStatus = null
                        }
                        
                        match.player1 = {id: player1.userId, name: player1.username, winner: usersInMatch[0].winStatus}
                        match.player2 = {id: player2.userId, name: player2.username, winner: usersInMatch[1].winStatus}
                    }


                    gamesHolder.games.push(match)
                    match = {}
                    matchesAdded++
                }
                if (matchesAdded === matchesNeededPerRound) {
                    this.rounds.push(gamesHolder)
                    gamesHolder = {games: []}
                    matchesAdded = 0
                    matchesNeededPerRound /= 2
                }
            }
        },
        getUserMatchLink() {
            this.isHost = this.currentTourney.tourneyHost === this.$store.state.user.username
            applicationServices.getUserMatchLink(this.currentTourney.tourneyId).then((response) => {
                if (response.status >= 200 && response.status < 300) {
                    this.userMatchLink = response.data
                    if (this.userMatchLink.length > 0) {
                        this.matchesInDb = true
                        this.setBracketData()
                    }
                }
            })
        },
        changeToWin(player) {
            if (this.isHost) {
                let userInMatch = this.userMatchLink.find((link) => {
                    return link.userId === player.id
                })
                userInMatch.winStatus = true

                let otherUserInMatch = this.userMatchLink.find((link) => {
                    return link.userId !== player.id && link.matchId === userInMatch.matchId
                })
                otherUserInMatch.winStatus = false

                applicationServices.updateWinStatus(userInMatch.winStatus, userInMatch.userId, userInMatch.matchId).then((response) => {
                    if (response.status === 200 || response.status === 201) {
                        applicationServices.updateWinStatus(otherUserInMatch.winStatus, otherUserInMatch.userId, otherUserInMatch.matchId).then((response) => {
                            if (response.status === 200 || response.status === 201) {
                                this.getUserMatchLink()
                            }
                        })
                    }
                })
            }
        },
        updateFollowUpMatch(currentMatchId, usersPrevMatch1, usersPrevMatch2) {
            let usersInFollowUpMatch = this.userMatchLink.filter((link) => {
                return link.matchId === currentMatchId
            })
            // Add new match to the follow up match data
            if (usersInFollowUpMatch.length === 0) {
                let winnerMatch1 = usersPrevMatch1.find((link) => {
                    return link.winStatus
                })
                let userWinner1 = this.usersInTourney.find((user) => {
                    return user.userId === winnerMatch1.userId
                })

                let winnerMatch2 = usersPrevMatch2.find((link) => {
                    return link.winStatus
                })
                let userWinner2 = this.usersInTourney.find((user) => {
                    return user.userId === winnerMatch2.userId
                })

                console.log(winnerMatch1)
                applicationServices.addUserToMatch(currentMatchId, userWinner1.username)
                applicationServices.addUserToMatch(currentMatchId, userWinner2.username)
            // Update current follow up match by adding the current winner and removing the previous one
            } else {

            }
        }
    },
    computed: {
        initialMatches() {
            return this.matchesInTourney.filter((match) => {
                return match.roundLevel === 0
            })
        }
    },
    created() {
        this.getUserMatchLink(),
        this.isHost = this.currentTourney.tourneyHost === this.$store.state.user.username
    }
}
</script>

<style scoped>
#bracket {
    display: flex;
    justify-content: center;
}
#generate-button {
    margin-top: 15px
}
.bracket-name {
    color: white;
}
</style>