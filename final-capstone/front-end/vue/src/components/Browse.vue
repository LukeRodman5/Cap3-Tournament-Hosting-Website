<template>
  <div class ="container">
    <table id="tblTournaments">

      <tr>
       <th class ="column" id ="column1">Tournament Name</th>
       <th class ="column" id ="column2">Description</th>       
       <th class ="column" id ="column3">Start Date</th>
       <th class ="column" id ="column4">End Date</th>
       <th class ="column" id ="column5">Host</th>
       <th class ="column" id ="column6">Status</th>
      </tr>
    
      <tr>
        <td>
          <input type="text" id="tournamentNameFilter"/>
        </td>
        <td>
          <input type="text" id="startDateFilter"/>
        </td>
        <td>
          <input type="text" id="endDateFilter"/>
        </td>
        <td>
          <input type="text" id="descriptionFilter"/>
        </td>
        <td>
          <input type="text" id="host"/>
        </td>
        <td>
          <select id="statusFilter">
          <option value> Show All </option>
          <option value = "Current">Current Events</option>
          <option value = "Upcoming">Upcoming Events</option>
          <option value = "Previous">Previous Events</option>
          </select>
        </td>
      </tr>

      <tr v-for="tournament in this.$store.state.tournaments" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="host">{{tournament.tourneyHost}}</td>
              <router-link v-bind:to="{name: 'tournament-detail', params: {tourneyID: tournament.tourneyId}}" tag="input" type="button" value="View Details"></router-link>
      </tr>
    </table>
 </div>
</template>

<script>
import applicationServices from '../services/ApplicationServices'

export default {
  name: "browse",
  methods: {
    viewTournament(id){
      this.$router.push(`/tournaments/${id}`)
    },//end viewTournament 
    getTournaments(){
      applicationServices.getTournaments().then(response =>{
          this.$store.commit("SET_TOURNAMENTS", response.data)
      }) //end of then
    }//end of getTournaments
  },//end of methods
  created() {
      this.getTournaments();
  }
}
</script>

<style>

 .tblTournaments {
     display: grid;
     grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
     grid-template-areas: 
     "column1 column2 column3 column4 column5 column6"
     ;

 }
 table{
   width: 100%;
 }
 #column1{
   grid-area: column1;
 }
  #column2{
   grid-area: column2;
 }
  #column3{
   grid-area: column3;
 }
  #column4{
   grid-area: column4;
 }
  #column5{
   grid-area: column5;
 }
  #column6{
   grid-area: column6;
 }

 #tournamentNameFilter{
   grid-area: column1;
 }
 #startDateFilter{
   grid-area: column2;
 }
 #endDateFilter{
   grid-area: column3;
 }
#descriptionFilter{
  grid-area: column4;
}
#host{
  grid-area: column5;
}
#statusFilter{
  grid-area: column6;
}

</style>