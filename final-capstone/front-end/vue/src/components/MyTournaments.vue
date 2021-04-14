<template>
<div>
    <!--display a list of tournaments where I am the host-->
    <h2> Tournaments I'm Hosting</h2>
    <table class = "host">    
        <thead>
          <th id="column1"> Tournament Name</th>
          <th id="column2"> Tournament Description</th>
          <th id="column3"> Start Date</th>
          <th id="column4"> End Date</th>
          <th id="column5"> Current Participants</th>
          <th id="column6"> Maximum Participants</th>
        </thead>
      <tbody id="tournament-host">
           <tr v-for="tournament in this.myHostTournaments" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="participants">{{tournament.numOfParticipants}}</td>
              <td class="max-participants">{{tournament.maxNumOfParticipants}}</td>
              <router-link v-bind:to="{name: 'manage-tournament', params: {tourneyID: tournament.tourneyId}}" tag="input" type="button" value="Manage"></router-link>
            </tr>
      </tbody>
    </table>
    <!--display a list of tournaments where I am participant-->
    <h2> Tournaments I'm Playing</h2>
    <table class = "play">
      <thead>
          <th id="column1"> Tournament Name</th>
          <th id="column2"> Tournament Description</th>
          <th id="column3"> Start Date</th>
          <th id="column4"> End Date</th>
          <th id="column5"> Current Participants</th>
          <th id="column6"> Maximum Participants</th>
        </thead>
      <tbody id="tournament-play">
            <tr v-for="tournament in this.myPlayTournaments" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="participants">{{tournament.numOfParticipants}}</td>
              <td class="max-participants">{{tournament.maxNumOfParticipants}}</td>
              <button class="btnLeaveTourney" v-on:click="leaveTourney(tournament.tourneyId)">Leave</button>
              <router-link v-bind:to="{name: 'tournament-detail', params: {tourneyID: tournament.tourneyId}}" tag="input" type="button" value="Details"></router-link>
            </tr>
      </tbody>
    </table>
    <h2> My Invites</h2>
    <table class = "play">
      <thead>
          <th id="column1"> Tournament Name</th>
          <th id="column2"> Tournament Description</th>
          <th id="column3"> Start Date</th>
          <th id="column4"> End Date</th>
          <th id="column5"> Current Participants</th>
          <th id="column6"> Maximum Participants</th>
        </thead>
      <tbody id="tournament-invites">
            <tr v-for="tournament in this.myInvites" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="participants">{{tournament.numOfParticipants}}</td>
              <td class="max-participants">{{tournament.maxNumOfParticipants}}</td>
              <button class="btnAcceptInvite" v-on:click="acceptInvite(currentTournament.tourneyId, $store.state.user.username)">Join</button>
              <button class="btnDeclineInvite" v-on:click="declineInvite(tournament.tourneyId)">Decline</button>
            </tr>
      </tbody>
    </table>
</div>
</template>

<script>
import applicationServices from '../services/ApplicationServices'

export default {
    name: "my-tournaments",
    data(){
        return{
            myTournament:{
                tourneyName: '',
                tourneyDesc:'',
                tourneyHost: '',
                startDate:'',
                endDate:'',
                active:true,
                openForReg:true,
                maxNumOfParticipants: 20,
                numOfParticipants:0
            },
            myHostTournaments:[],
            myPlayTournaments:[],
            myInvites:[]
        }//end of return
    },//end of data
    created(){
      this.getMyHostTournaments(),
      this.getMyPlayTournaments(),
      this.getMyInvites 
    },//end of create
    methods:{
      getMyHostTournaments(){
        this.myHostTournaments = this.$store.state.tournaments.filter(tournament=>tournament.tourneyHost===this.$store.state.user.username)
      },
     getMyPlayTournaments(){
        applicationServices.getTourneysByName(this.$store.state.user.username, "Approved").then(response =>{
          this.$store.commit("SET_MY_TOURNAMENTS", response.data)
          this.myPlayTournaments = this.$store.state.myTournaments
        })
      },
      getMyInvites(){
        applicationServices.getTourneysByName(this.$store.state.user.username, "Invited").then(response =>{
          this.$store.commit("SET_MY_INVITES", response.data)
          this.myInvites = this.$store.state.myInvites
        })
      },
      leaveTourney(tourneyId){
        console.log(tourneyId)
        console.log(this.$store.state.user.username)
      applicationServices.leaveTourney(tourneyId, this.$store.state.user.username)
      .then(response=>{
      console.log(response.status)
        if(response.status>=200 && response.status<300){
          alert("You have successfully left this tournament.")
        }//end of if
        else{
          alert("Attempt to leave this tournament was unsuccessful.")
        }//end of else
        this.$router.push("/")
      } //end of then
      )},//end of leaveTourney
      declineInvite(tourneyId){
        applicationServices.leaveTourney(tourneyId, this.$store.state.user.username)
        .then(response=>{
          console.log(response.status)
          if(response.status>=200 && response.status<300){
            alert("You have declined this tournament")
          }
          else{
            alert("Failed. This tournament has not been declined.")
          }
        })
      },//end of declineInvite

      acceptInvite(tourneyId){
       applicationServices.changeUserTourneyStatus(tourneyId, this.$store.state.user.username, "Approved")
       .then(response=>{
         console.log(response.status)
         if(response.status>=200 && response.status<300){
           alert("Invitation accepted. You have successfully joined this tournament.")
         }//end of if
         else{
           alert("Failed. This tournament has not been joined.")
         }//end of else
       }//end of then
       )}//end of acceptInvite
    }   
}
</script>

<style>
.name{
  text-align: center;
}
.description{
  text-align: center;
}
.start-date{
  text-align: center;
}
.end-date{
  text-align: center;
}
.participants{
  text-align: center;
}
.max-participants{
  text-align: center;
}

#tournament-host{

 box-shadow: 0px 5px 5px rgb(112, 112, 112);
}

#tournament-play{

 box-shadow: 0px 5px 5px rgb(112, 112, 112);
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
  #name{
    grid-area: column1;
  }
   #description{
     grid-area: column2;
   }


</style>
