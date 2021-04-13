<template>
    <form v-on:submit.prevent="addTournament">
        <label for="name">Tournament Name:</label>
        <input id="name" type="text" v-model="newTournament.tourneyName" />

        <label for="description"> Tournament Description:</label>
        <input id="name" type="text" v-model="newTournament.tourneyDesc"/>

        <label for ="start-date"> Start Date:</label>
        <input id="start-date" type="date" v-model="newTournament.startDate"/>

        <label for ="end-date"> End Date:</label>
        <input id="end-date" type="date" v-model="newTournament.endDate"/>

        <label for ="open-reg"> Open For Registration? </label>
        <input id="open-reg" type="checkbox" v-model="newTournament.openForReg"/>

        <label for ="participantMax"> Max Participants: </label>
        <!-- <input id="participantMax" type="text" v-model="newTournament.maxNumOfParticipants"/> -->
        <select id="participantMax" v-model="newTournament.maxNumOfParticipants">
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
    name: "add-tournament",
    data(){
        return{
            newTournament:{
                tourneyName: '',
                tourneyDesc:'',
                tourneyHost: this.$store.state.user.username,
                startDate: '',
                endDate: '',
                active:true,
                openForReg:true,
                maxNumOfParticipants: 20,
                numOfParticipants:0
            },
            tournaments:[]
        }//end of return
    },//end of data
    methods:{
      addTournament(){
            console.log(this.newTournament)
             applicationServices.addTournament(this.newTournament).then(response =>{
             if(response.status === 201 || response.status === 200){
                     // this.getTournaments()
                    this.newTournament={
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
