<template>
  <table>
      <!-- <thead>
          <tr>
              <th>Tournament Name</th>
              <th>Description</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Host</th>
          </tr>
      </thead> -->
      <!-- copied to browse -->
      <tbody id="tournament">
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
//  copied to browse
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
 .tournament {
     display: grid;
     grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr ;
     grid-template-areas: 
    "name description start-date end-date host"
    
     ;
     background-color: lightcoral;
 }
 #name{
     grid-area: name;
 }
 #description{
     grid-area: description;
 }
 #start-date{
     grid-area: start-date;
 }
 #end-date{
     grid-area: end-date;
 }
 #host{
     grid-area: host;
 }

</style>