<template>
<div>
    display a list of tournaments where I am the host
    display a list of tournaments where I am participant
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
    }   
}
</script>

<style>

</style>
