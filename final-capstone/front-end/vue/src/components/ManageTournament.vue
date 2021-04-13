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
                    <input id="name" type="text" v-model="updatedTournament.tourneyName" /></td>
            </tr>
            <tr>
                <td>Description</td>
                <td>{{this.currentTournament.tourneyDesc}}</td>
                <td><label for="description"></label>
                    <input id="name" type="text" v-model="updatedTournament.tourneyDesc"/></td>
            </tr>
            <tr>
                <td>Start Date</td>
                <td>{{this.currentTournament.startDate}}</td>
                <td><label for ="start-date"></label>
                    <input id="start-date" type="date" v-model="updatedTournament.startDate"/></td>
            </tr>
            <tr>
                <td>End Date</td>
                <td>{{this.currentTournament.endDate}}</td>
                <td><label for ="end-date"></label>
                <input id="end-date" type="date" v-model="updatedTournament.endDate"/></td>
            </tr>
            <tr>
                <td>Open for Registration?</td>
                <td>{{this.currentTournament.openForReg}}</td>
                <td><label for ="open-reg"></label>
                    <input id="open-reg" type="checkbox" v-model="updatedTournament.openForReg"/></td>
            </tr>
            <tr>
                <td>Maximum Participants</td>
                <td>{{this.currentTournament.maxNumOfParticipants}}
                <td><label for ="participantMax"></label>
                    <select id="participantMax" v-model="updatedTournament.maxNumOfParticipants">
                        <option value=4>4</option>
                        <option value=8>8</option>
                        <option value=16>16</option>                        
                        <option value=32>32</option>
                    </select>
                </td>
            </tr>
        </table>
    <button type="submit" class="btn save">Save Changes</button>
    <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button> 
    </form>
</template>

<script>
import applicationServices from '../services/ApplicationServices'

export default {
    name: "manage-tournament",
    data(){
        return{
            updatedTournament:{
                tourneyId: this.$route.params.tourneyID,
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
          )},//end of retreiveTournament  
      saveTournament(){
          if(this.updatedTournament.tourneyName===''){
              this.updatedTournament.tourneyName=this.currentTournament.tourneyName
          }
          if(this.updatedTournament.tourneyDesc===''){
              this.updatedTournament.tourneyDesc=this.currentTournament.tourneyDesc
          }
          if(this.updatedTournament.startDate===''){
              this.updatedTournament.startDate=this.currentTournament.startDate
          }
          if(this.updatedTournament.endDate===''){
              this.updatedTournament.endDate=this.currentTournament.endDate
          }
      },//end of saveTournament
      saveManageTournament(){
        this.saveTournament()
        applicationServices.updateTournament(this.updatedTournament, this.$route.params.tourneyID).then(response =>{
             if(response.status === 201 || response.status === 200){
                console.log(this.updatedTournament)
                console.log(response.status)
                console.log(response.data)
                this.updatedTournament=response.data
                console.log(this.updatedTournament)
                
                alert("You have succesfully updated the tournament")
                this.$router.push("/")
            }//end of if 
           /* else{
                alert("Update to tournament has failed.")
            }  */
        })//end then
    },//end saveManageTournament
    cancelForm(){
        this.$router.push(`/tournaments/${this.$store.state.user.username}`)

    }
}//end of methods
}//end of export
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
