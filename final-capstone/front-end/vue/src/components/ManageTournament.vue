<template>
    <form v-on:submit.prevent="saveManageTournament">
        <table>
            <thead>
                <td></td>
                <td>Current Information</td>
                <td>New Information</td>
            </thead>
            <tr>
                <td>Tournament Name</td>
                <td>{{this.currentTournament.tourneyName}}</td>
                <td><label for="name"></label>
                    <input id="name" type="text" v-model="tournament.tourneyName" /></td>
            </tr>
            <tr>
                <td>Description</td>
                <td>{{this.currentTournament.tourneyDesc}}</td>
                <td><label for="description"></label>
                    <input id="name" type="text" v-model="tournament.tourneyDesc"/></td>
            </tr>
            <tr>
                <td>Start Date</td>
                <td>{{this.currentTournament.startDate}}</td>
                <td><label for ="start-date"></label>
                    <input id="start-date" type="date" v-model="tournament.startDate"/></td>
            </tr>
            <tr>
                <td>End Date</td>
                <td>{{this.currentTournament.endDate}}</td>
                <td><label for ="end-date"></label>
                <input id="end-date" type="date" v-model="tournament.endDate"/></td>
            </tr>
            <tr>
                <td>Open for Registration?</td>
                <td>{{this.currentTournament.openForReg}}</td>
                <td><label for ="open-reg"></label>
                    <input id="open-reg" type="checkbox" v-model="tournament.openForReg"/></td>
            </tr>
            <tr>
                <td>Maximum Participants</td>
                <td>{{this.currentTournament.maxNumOfParticipants}}
                <td><label for ="participantMax"></label>
                    <select id="participantMax" v-model="tournament.maxNumOfParticipants">
                        <option value=4>4</option>
                        <option value=8>8</option>
                        <option value=16>16</option>                        
                        <option value=32>32</option>
                    </select>
                </td>
            </tr>
        </table>
    <button type="submit" class="btn save">Save Changes</button> 
    </form>
</template>

<script>
import applicationServices from '../services/ApplicationServices'

export default {
    name: "manage-tournament",
    data(){
        return{
            tournament:{
                tourneyName:'',
                tourneyDesc:'',
                tourneyHost: this.$store.state.user.username,
                startDate:'',
                endDate:'',
                active:true,
                openForReg:true,
                maxNumOfParticipants: 4,
                numOfParticipants:0
            },
            currentTournament: {}
        }//end of return
    },//end of data
    created(){
      this.retrieveTournament()
    
    },//end of creates
    methods:{
      retrieveTournament() {
      applicationServices
        .getTournament(this.$route.params.tourneyID)
        .then(response => {
          this.currentTournament = response.data
              }
          )}
      },//end of retreiveTournament  
      saveTournament(){
          if(this.tournament.tourneyName===''){
              this.tournament.tourneyName=this.currentTournament.tourneyName
          }
          if(this.tournament.tourneyDesc===''){
              this.tournament.tourneyDesc=this.currentTournament.tourneyDesc
          }
          if(this.tournament.startDate===''){
              this.tournament.startDate=this.currentTournament.startDate
          }
          if(this.tournament.endDate===''){
              this.tournament.endDate=this.currentTournament.endDate
          }
      },//end of saveTournament
      saveManageTournament(){
        this.saveTournamant()
        applicationServices.updateTournament(this.tournament, this.$route.params.tourneyID).then(response =>{
             if(response.status === 201 || response.status === 200){
                this.tournament={
                    tourneyName: '',
                    tourneyDesc:'',
                    tourneyHost: this.$store.state.user.username,
                    startDate:'',
                    endDate:'',
                    active:true,
                    openForReg:true,
                    maxNumOfParticipants: '',
                    numOfParticipants:0
                    }
                alert("You have succesfully updated the tournament")
                this.$router.push("/")
            }//end of if 
            else{
                alert("Update to tournament has failed.")
            }
        })//end then
        
    //end saveManageTournament
        
}   


}
</script>

<style scoped>
form {
    display: flex;
    flex-direction: column;
    align-items: center;
}
form > * {
    margin: 5px;
}
</style>
