<template>
  <table>
      <thead>
          <tr>
              <th>&nbsp;</th>
              <th>Tournament Name </th>
              <th>Description</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Host</th>
          </tr>
      </thead>
      <tbody>
            <tr v-for="tournament in tournaments" :key="tournament.tourneyID">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}
              <td class="host">{{tournament.tourneyHost}}
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
            this.$router.push(`/tournaments/${tourneyID}`)
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