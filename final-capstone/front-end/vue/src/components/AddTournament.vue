<template>
    <form v-on: submit.prevent="addTournament">
     <div class="form-element">
        <label for="name">Tournament Name:</label>
        <input id="name" type="text" v-model="newTournament.name" />
    </div>
    <div class="form-element">
        <label for="description"> Tournament Description:</label>
        <input id="name" type="text" v-model="newTournament.description"/>
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
        <input id="participantMax" type="text" v-model="newTournament.participantMax"/>
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
                name: '',
                description:'',
                host: this.$store.state.user.username,
                startDate:'',
                endDate:'',
                isActive:true,
                openForReg:true,
                participantMax: 20,
                participantNum:0
            },
            tournaments:[]
        }//end of return
    },//end of data
    methods:{
        addTournament(){
            const newTourney ={
                
            }
        }
        /* addTournament(){
            applicationServices.addTournament(this.newTournament).then(response =>{
                if(response.status === 201){
                    // this.getTournaments()
                    this.newTournament={
                        name: '',
                        description:'',
                        host: this.$store.state.user.username,
                        startDate:'',
                        endDate:'',
                        isActive:true,
                        openForReg:true,
                        participantMax: 20,
                        participantNum:0}
                this.$router.push("/")
                }//end of if */
            })//end then
        },//end add tournament
        
    }


}
</script>

<style>

</style>
