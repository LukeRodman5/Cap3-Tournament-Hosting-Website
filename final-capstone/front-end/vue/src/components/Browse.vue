<template>
  <div class ="container">
    <table id="tblTournaments">

      <tr>
       <th class ="column" id ="column1">Tournament Name</th>
       <th class ="column" id ="column2">Description</th>       
       <th class ="column" id ="column3">Start Date</th>
       <th class ="column" id ="column4">End Date</th>
       <th class ="column" id ="column5">Host</th>
       <!-- <th class ="column" id ="column6">Status</th> -->
      </tr>
    
      <tr>
        <td>
          <input type="text" id="tournamentNameFilter" v-model="filter.tourneyName" />
        </td>
        <td>
          <!-- <input type="text" id="descriptionFilter" v-model="filter.tourneyDesc" /> -->
        </td>        
        <td>
          <input type="date" id="startDateFilter" v-model="filter.startDate" />
        </td>
        <td>
          <input type="date" id="endDateFilter" v-model="filter.endDate" />
        </td>
        <td>
          <input type="text" id="host" v-model="filter.tourneyHost" />
        </td>
        <td>
          <!-- <select id="statusFilter" v-model="filter.status">
          <option value> Show All </option>
          <option value = "Current">Current Events</option>
          <option value = "Upcoming">Upcoming Events</option>
          <option value = "Previous">Previous Events</option>
          </select> -->
        </td>
      </tr>

      <tr class="browse-list" v-for="tournament in filteredList" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}} </td>
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
import moment from 'moment'
export default {
  name: "browse",
  data(){
    return {
      filter: {
        tourneyName: "",
        tourneyDesc: "",
        startDate: null,
        endDate: null,
        tourneyHost: "",
        status: ""
      },
      tournaments:[]
    }
  },

  methods: {
    viewTournament(id){
      this.$router.push(`/tournaments/${id}`)
    },//end viewTournament 
    getTournaments(){
          let currentDate = moment().format()
      applicationServices.getTournaments().then(response =>{
          this.$store.commit("SET_TOURNAMENTS", response.data)
          this.tournaments=response.data
          
          if(this.tournaments.endDate < currentDate) {
            this.tournaments.status = "previous"
          }
      }) //end of then
    }//end of getTournaments
  },//end of methods
  computed: {
    filteredList(){

      let filteredTournaments = this.tournaments
      // if(this.filter.endDate < currentDate) {
      //   this.filter.status = "previous"
      // }
      // if(this.filter.startDate > currentDate) {
      //   this.filter.status = "upcoming"
      // }
      // if(this.filter.startDate <= currentDate && this.filter.endDate >= currentDate) {
      //   this.filter.status = "current"
      // }

      if (this.filter.tourneyName != "") {
        filteredTournaments = filteredTournaments.filter((tournament) =>
        tournament.tourneyName
          .toLowerCase()
          .includes(this.filter.tourneyName.toLowerCase())
        )
      }
      if (this.filter.tourneyDesc != "") {
        filteredTournaments = filteredTournaments.filter((tournament) =>
        tournament.tourneyDesc
          .toLowerCase()
          .includes(this.filter.tourneyDesc.toLowerCase())
        )
      }
      if (this.filter.tourneyHost != "") {
        filteredTournaments = filteredTournaments.filter((tournament) =>
        tournament.tourneyHost
          .toLowerCase()
          .includes(this.filter.tourneyHost.toLowerCase())
        )
      }
      if(this.filter.status != null) {
        filteredTournaments = filteredTournaments.filter((tournament) =>
        tournament.status = "previous")
      }
      // if(this.filter.endDate != null) {
      //   filteredTournaments = filteredTournaments.filter((tournament) =>
      //   tournament.endDate <= this.filter.endDate)
      // }
      // if(this.filter.status = "previous") {
      //   filteredTournaments = filteredTournaments.filter((tournament) => 
      //   tournament.status === this.filter.status)
      // }
     
      return filteredTournaments
    }
  },
  created() {
      this.getTournaments();
  }
}
</script>

<style>

 #tblTournaments {

 padding-top: 20px;
 padding-bottom: 300px;
 padding-left:70px;
 padding-right:70px;
 box-shadow: 0px 10px 10px rgb(112, 112, 112);

 }
 table{
   width: 100%;
   background-color: rgb(255, 255, 255);
   
 }
 #column1{
   grid-area: column1;
   background-color: rgb(235, 235, 235);
   
 }
  #column2{
   grid-area: column2;
   background-color: rgb(235, 235, 235);
   
 }
  #column3{
   grid-area: column3;
   background-color: rgb(235, 235, 235);
 }
  #column4{
   grid-area: column4;
   background-color: rgb(235, 235, 235);
   
 }
  #column5{
   grid-area: column5;
   background-color: rgb(235, 235, 235);
 }
  #column6{
   grid-area: column6;
   background-color: rgb(235, 235, 235);
 }

.description{
 max-width: 250px;
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
.browse-list{
  /* display:grid */
  /* grid-template-columns: "name description start-date end-date host tournament-detal"; */
  /* background-color: lightgreen; */
  /* border: 10px solid black */
  /* ; */
}

.container{
  text-align: center;
  margin-right: 2%;
  margin-left: 2%;
}

</style>