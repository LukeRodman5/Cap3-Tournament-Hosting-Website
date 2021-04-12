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
      <tbody id="tournament host">
           <tr v-for="tournament in this.myHostTournaments" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="participants">{{tournament.numOfParticipants}}</td>
              <td class="max-participants">{{tournament.maxNumOfParticipants}}</td>
              <router-link v-bind:to="{name: 'tournament-manage', params: {tourneyID: tournament.tourneyId}}" tag="input" type="button" value="Manage Tournament"></router-link>
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
      <tbody id="tournament play">
            <tr v-for="tournament in this.myPlayTournaments" :key="tournament.tourneyId">
              <td class="name">{{tournament.tourneyName}}</td>
              <td class="description">{{tournament.tourneyDesc}}</td>
              <td class="start-date">{{tournament.startDate}}</td>
              <td class="end-date">{{tournament.endDate}}</td>
              <td class="participants">{{tournament.numOfParticipants}}</td>
              <td class="max-participants">{{tournament.maxNumOfParticipants}}</td>
              <button class="btnLeaveTourney" v-on:click="leaveTourney(currentTournament.tourneyId, $store.state.user.username)">Leave Tournament</button>
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
      this.getMyHostTournaments()/* ,
      this.getMyPlayTournaments() */
    },//end of create
    methods:{
      getMyHostTournaments(){
        this.myHostTournaments = this.$store.state.tournaments
        for(let i = 0; i<this.myHostTournaments.length; i++){
          applicationServices.getHostUsernameByTourneyID(this.myHostTournaments[i].tourneyId).then(response=>
          this.myHostTournaments[i].tourneyHost = response.data
          )
        }
        this.myHostTournaments.filter(tournament=>tournament.tourneyHost===this.$store.state.user.username)
      },
     /*  getMyPlayTournaments(username){
        applicationServices.getTourneysByName(username).then(response =>{
          this.$store.commit("SET_TOURNAMENTS", response.data)
        })
        this.myPlayTournaments=this.$store.state.tournaments
      }, */
      leaveTourney(tourneyId, username){
      applicationServices.leaveTourney(tourneyId, username)
    .then(response=>{
        if(response.status===200 || response.status===201){
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

</style>
