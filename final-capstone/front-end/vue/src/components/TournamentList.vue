<template>
  <table>
      <thead>
          <tr>
              <th>&nbsp;</th>
              <th>Tournament Name</th>
              <th>Description</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Host</th>
          </tr>
      </thead>
      <tbody>
            <tr v-for="tournament in this.$store.state.tournaments" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="host">{{tournament.tourneyHost}}</td>
              <!-- <input class = "details-button" type="button" value="View Details" /> -->
              <router-link v-bind:to="{name: 'tournament-detail', params: {tourneyID: tournament.tourneyId}}" tag="input" type="button" value="View Details"></router-link>
            </tr>
      </tbody>
  </table>
</template>

<script>
import applicationServices from '../services/ApplicationServices'

export default {
    name: "tournament-list",
    methods:{
        viewTournament(id){
            this.$router.push(`/tournaments/${id}`)
        },//end viewTournament 
        getTournaments(){
            applicationServices.getTournaments().then(response =>{
                this.$store.commit("SET_TOURNAMENTS", response.data)
            }) //end of then
        }//end of getTournaments
    },//end of methods
    created(){
        this.getTournaments();
    },
    computed:{
       }//end of computed
    }//end of export

</script>

<style>

</style>