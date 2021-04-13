<template>
<div id="set-bracket-area">
    <h3>Generate Tournament Helper</h3>
    <p>Hello! As host, use the below form to setup the tournament.</p>
    <div v-for="match in matchesInTourney" v-bind:key="match.matchId">
    <table>
        <tr>
            <td>test</td>
            <td></td>
        </tr>
    </table>
    </div>
    <input type="button" value="Generate Tournament" v-on:click.prevent="addToDatabase" />
</div>
</template>

<script>
import applicationServices from '@/services/ApplicationServices.js'

export default {
    name: 'generate-tournament',
    props: ["currentTourney", "usersInTourney", "matchesInTourney"],
    // data() {
        
    // },
    // methods: {
    //     getUsersInTourney() {
    //         applicationServices.getUsersInTourney(this.$route.params.tourneyID).then((response) => {
    //             if (response.status === 200 || response.status === 201) {
    //                 this.usersInTourney = response.data
    //             } else {
    //                 console.log("Error")
    //             }
    //         })
    //     },
    //     getTotalMatchesNeeded() {
    //         let count = 0
    //         let matchesPerRound = this.currentTourney.maxNumOfParticipants / 2
    //         for (let i = 0; i < matchesPerRound; i++) {
    //             this.matchesNeeded[count] = {}
    //             count++
    //             if (i === matchesPerRound - 1 && matchesPerRound > 1) {
    //                 i = 0
    //                 matchesPerRound = matchesPerRound / 2
    //             }
    //         }
    //     }
    // }
}
</script>

<style>

</style>