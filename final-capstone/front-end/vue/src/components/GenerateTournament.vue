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
            {{ player.name }}
          </template>
      </bracket>
    </div>
</div>
</template>

<script>
import applicationServices from '@/services/ApplicationServices.js'
import Bracket from "vue-tournament-bracket";

const tbdPlayers = { name: "TBD", winner: null }

export default {
    name: 'generate-tournament',
    props: ["currentTourney", "usersInTourney", "matchesInTourney"],
    components: {
        Bracket
    },
    data() {
        return {
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
            let matchCount = 0;

            this.initialMatches.forEach((iniMatch) => {
                let player1 = this.usersInTourney.find((user) => {
                    return user.username === iniMatch.player1
                })
                let player2 = this.usersInTourney.find((user) => {
                    return user.username === iniMatch.player2
                })
                match.player1 = {id: player1.userId, name: player1.username, winner: null}
                match.player2 = {id: player2.userId, name: player2.username, winner: null}
                gamesHolder.games.push(match)
                match = {}
                matchCount++
            })

            let iniMatchCount = this.currentTourney.maxNumOfParticipants / 2
            if (matchCount === iniMatchCount) {
                this.rounds.push(gamesHolder)
                gamesHolder = {games: []}
                iniMatchCount = iniMatchCount / 2
                matchCount = 0
            }

            for (let i = matchCount; i < iniMatchCount; i++) {
                match = {player1: tbdPlayers, player2: tbdPlayers}
                gamesHolder.games.push(match)
                match = {}

                if (i === iniMatchCount - 1) {
                    this.rounds.push(gamesHolder)
                    gamesHolder = {games: []}
                    if (iniMatchCount >= 1) {
                        i = -1
                        iniMatchCount = iniMatchCount / 2
                    }
                }
            }
        },
        getUserMatchLink() {
            applicationServices.getUserMatchLink(this.currentTourney.tourneyId).then((response) => {
                if (response.status >= 200 && response.status < 300) {
                    this.userMatchLink = response.data
                    if (this.userMatchLink.length > 0) {
                        this.matchesInDb = true
                        console.log(this.userMatchLink)
                    }
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

</style>