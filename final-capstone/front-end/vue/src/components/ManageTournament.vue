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
                <td>v-bind="this.tournament.tourneyName"
        </table>

        <label for="name">Tournament Name:</label>
        <input id="name" type="text" v-model="this.tournament.tourneyName" />

        <label for="description"> Tournament Description:</label>
        <input id="name" type="text" v-model="this.tournament.tourneyDesc"/>

        <label for ="start-date"> Start Date:</label>
        <input id="start-date" type="date" v-model="this.tournament.startDate"/>

        <label for ="end-date"> End Date:</label>
        <input id="end-date" type="date" v-model="this.tournament.endDate"/>

        <label for ="open-reg"> Open For Registration? </label>
<<<<<<< HEAD
        <input id="open-reug" type="checkbox" v-model="tournament.openForReg"/>
=======
        <input id="open-reg" type="checkbox" v-model="this.tournament.openForReg"/>
>>>>>>> cd4a4eecd78e4073768a1a3221553d737326900d

        <label for ="participantMax"> Max Participants: </label>
        <select id="participantMax" v-model="this.tournament.maxNumOfParticipants">
            <option value=4>4</option>
            <option value=8>8</option>
            <option value=16>16</option>                        
            <option value=32>32</option>
        </select>    

    <button type="submit" class="btn save">Save Tournament</button> 
    </form>
</template>

<script>
import applicationServices from '../services/ApplicationServices'

export default {
    name: "manage-tournament",
    data(){
        return{
            tournament:{
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
      saveManageTournament(){
            console.log(this.tournament)
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
                        maxNumOfParticipants: 20,
                        numOfParticipants:0
            }
                 this.$router.push("/")
            }//end of if 
        })//end then
    },//end add tournament
        
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
