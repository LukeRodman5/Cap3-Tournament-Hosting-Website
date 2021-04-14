<template>
<div id="bracket-generator">
    <div id="set-bracket-area"  v-if="!matchesInDb">
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
    <input type="button" 
        v-bind:value="(usersInTourney.length !== initialMatches.length * 2) ? 'Not enough users!' : 'Generate Tournament'"
        v-bind:disabled="usersInTourney.length !== initialMatches.length * 2" 
        v-on:click.prevent="addToDatabase()"
    />
    </div>
    <div id="bracket">
      <bracket :rounds="rounds">
        <template slot="player" slot-scope="{ player }">
            <div v-on:click="changeStatus(player)">{{ player.name }}</div>
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
                        console.log('Succuess')
                    } else {
                        console.log('Error')
                    }
                })
                applicationServices.addUserToMatch(iniMatch.matchId, iniMatch.player2).then((response) => {
                    if (response.status >= 200 && response.status < 300) {
                        console.log('Succuess')
                    } else {
                        console.log('Error')
                    }
                })
            })
            this.matchesInDb = true
            this.displayBracket()
        },
        displayBracket() {
            let gamesHolder = {games: []}
            let match = {}
            let matchCount = 0
            let roundLevel = 0

            this.initialMatches.forEach((iniMatch) => {
                let userIdsInMatch = this.userMatchLink.filter((link) => {
                    return link.matchId === iniMatch.matchId
                })
                let player1 = this.usersInTourney.find((user) => {
                    return user.userId === userIdsInMatch[0].userId
                })
                let player2 = this.usersInTourney.find((user) => {
                    return user.userId === userIdsInMatch[1].userId
                })
                if (!userIdsInMatch[0].winStatus && !userIdsInMatch[1].winStatus) {
                    userIdsInMatch[0].winStatus = null
                    userIdsInMatch[1].winStatus = null
                }
                match.player1 = {id: player1.userId, name: player1.username, winner: userIdsInMatch[0].winStatus}
                match.player2 = {id: player2.userId, name: player2.username, winner: userIdsInMatch[1].winStatus}

                gamesHolder.games.push(match)
                match = {}
                matchCount++
            })

            let matchCountPerRound = this.currentTourney.maxNumOfParticipants / 2
            if (matchCount === matchCountPerRound) {
                this.rounds.push(gamesHolder)
                gamesHolder = {games: []}
                matchCountPerRound = matchCountPerRound / 2
                matchCount = 0
            }

            for (let i = matchCount; i < matchCountPerRound; i++) {
                // if (roundLevel > 0) {
                //     for (let j = 0; j < this.rounds[0].length; j +=2) {
                //         console.log('Hello')
                //         if ((this.rounds[roundLevel - 1].games[j].player1.winner === true ||
                //             this.rounds[roundLevel - 1].games[j].player2.winner === true) &&
                //             (this.rounds[roundLevel - 1].games[j + 1].player1.winner === true ||
                //             this.rounds[roundLevel - 1].games[j + 1].player1.winner === true)) {
                //             let match1Winner = this.rounds[roundLevel - 1].games[j].player1.winner === true ? this.rounds[roundLevel - 1].games[j].player1 : this.rounds[roundLevel - 1].games[j].player2
                //             let match2Winner = this.rounds[roundLevel - 1].games[j + 1].player1.winner === true ? this.rounds[roundLevel - 1].games[j + 1].player1 : this.rounds[roundLevel - 1].games[j + 1].player2
                //             match = {player1: match1Winner, player2: match2Winner}
                //         }
                //     }
                // } else {
                    match = {player1: tbdPlayers, player2: tbdPlayers}
                // }
                gamesHolder.games.push(match)
                match = {}

                if (i === matchCountPerRound - 1) {
                    this.rounds.push(gamesHolder)
                    roundLevel++
                    gamesHolder = {games: []}
                    if (matchCountPerRound >= 1) {
                        i = -1
                        matchCountPerRound = matchCountPerRound / 2
                    }
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
                        this.displayBracket()
                    }
                }
            })
        },
        changeStatus(player) {
            let userInMatch = this.userMatchLink.find((link) => {
                return link.userId === player.id
            })
            userInMatch.winStatus = true
            applicationServices.updateWinStatus(userInMatch.winStatus, userInMatch.userId, userInMatch.matchId).then((response) => {
                if (response.status === 200 || response.status === 201) {
                    player.winner = true
                }
            })
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
        this.getUserMatchLink()
    }
}
</script>

<style scoped>
#bracket {
    display: flex;
    justify-content: center;
}
</style>