<template>
<div>
    <!--display a list of tournaments where I am the host-->
    <table class = "host">
        <thead>
            <h2> Tournaments I'm Hosting</h2>
        </thead>
      <tbody id="tournament host">
            <tr v-for="tournament in this.$store.state.tournaments" :key="tournament.tourneyId">
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
    <table class = "play">
        <thead>
            <h2> Tournaments I'm Playing</h2>
        </thead>
      <tbody id="tournament play">
            <tr v-for="tournament in this.$store.state.tournaments" :key="tournament.tourneyId">
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
                tourneyHost: this.$store.state.user.username,
                startDate:'',
                endDate:'',
                active:true,
                openForReg:true,
                maxNumOfParticipants: 20,
                numOfParticipants:0
            },
            tournaments:[]
        }//end of return
    },//end of data
    methods:{
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
    
    )}//end of joinTourney
    }   
}
</script>

<style>

</style>
