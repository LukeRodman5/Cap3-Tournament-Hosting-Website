<template>
    <form v-on:submit.prevent="addTournament">
     <div class="form-element">
        <label for="name">Tournament Name:</label>
        <input id="name" type="text" v-model="newTournament.tourneyName" />
    </div>
    <div class="form-element">
        <label for="description"> Tournament Description:</label>
        <input id="name" type="text" v-model="newTournament.tourneyDesc"/>
    </div>
    <div class ="form-element">
        <label for ="start-date"> Start Date:</label>
        <input id="start-date" type="date" v-model="newTournament.startDate"/>
    </div> 
    <div class ="form-element">
        <label for ="end-date"> End Date:</label>
        <input id="end-date" type="date" v-model="newTournament.endDate"/>
    </div> 
    <div class ="form-element">
        <label for ="open-reg"> Open For Registration? </label>
        <input id="open-reg" type="checkbox" v-model="newTournament.openForReg"/>
    </div>
    <div class ="form-element">
        <label for ="participantMax"> Max Participants: </label>
        <input id="participantMax" type="text" v-model="newTournament.maxNumOfParticipants"/>
    </div>
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

<style>

</style>
