<template>
<div>
    <!--display a list of tournaments where I am the host-->
    <h2> Tournaments I'm Hosting</h2>
    <table class = "host">    
        <thead>
          <th> Tournament Name</th>
          <th> Tournament Description</th>
          <th> Start Date</th>
          <th> End Date</th>
          <th> Current Participants</th>
          <th> Maximum Participants</th>
        </thead>
      <tbody id="tournament-host">
           <tr v-for="tournament in this.myHostTournaments" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="participants">{{tournament.numOfParticipants}}</td>
              <td class="max-participants">{{tournament.maxNumOfParticipants}}</td>
              <router-link v-bind:to="{name: 'manage-tournament', params: {tourneyID: tournament.tourneyId}}" tag="input" type="button" value="Manage Tournament"></router-link>
            </tr>
      </tbody>
    </table>
    <!--display a list of tournaments where I am participant-->
    <h2> Tournaments I'm Playing</h2>
    <table class = "play">
      <thead>
          <th> Tournament Name</th>
          <th> Tournament Description</th>
          <th> Start Date</th>
          <th> End Date</th>
          <th> Current Participants</th>
          <th> Maximum Participants</th>
        </thead>
      <tbody id="tournament-play">
            <tr v-for="tournament in this.myPlayTournaments" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="participants">{{tournament.numOfParticipants}}</td>
              <td class="max-participants">{{tournament.maxNumOfParticipants}}</td>
              <button class="btnLeaveTourney" v-on:click="leaveTourney(tournament.tourneyId)">Leave Tournament</button>
              <router-link v-bind:to="{name: 'tournament-detail', params: {tourneyID: tournament.tourneyId}}" tag="input" type="button" value="Tournament Details"></router-link>
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
            myPlayTournaments:[]
        }//end of return
    },//end of data
    created(){
      this.getMyHostTournaments() ,
      this.getMyPlayTournaments() 
    },//end of create
    methods:{
      getMyHostTournaments(){
        this.myHostTournaments = this.$store.state.tournaments.filter(tournament=>tournament.tourneyHost===this.$store.state.user.username)
      },
     getMyPlayTournaments(){
        applicationServices.getTourneysByName(this.$store.state.user.username).then(response =>{
          this.$store.commit("SET_MY_TOURNAMENTS", response.data)
          this.myPlayTournaments = this.$store.state.myTournaments
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
    )}//end of leaveTourney
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


</style>
