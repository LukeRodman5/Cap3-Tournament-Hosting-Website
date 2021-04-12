/**************************************************************************************
* This file is provided for you to use for any Application services you may create
*
*  If you would prefer a file with a different name for your services, 
*     simply create one 
***************************************************************************************/

import axios from 'axios'

export default{
    addTournament(tournament){
        return axios.post('/tournaments',tournament)
    },

    getTournaments(){
        return axios.get('/tournaments')
    },
    getAllUsers(){
        return axios.get('/users')
    },

    getTournament(tourneyID){
        return axios.get(`/tournaments/${tourneyID}`)
    },
    deleteTournament(tourneyID){
        return axios.delete(`/tournaments/${tourneyID}`)
    },
    joinTourney(tourneyID, username){
        return axios.post(`/tournaments/${tourneyID}/${username}`)
    },
    leaveTourney(tourneyID, username){
        return axios.delete(`/tournaments/users/${username}/${tourneyID}`)
    },
    updateTournament(tournament, tourneyID){
        return axios.put(`/tournaments/${tourneyID}`, tournament)
    },
    getTourneysByName(username){
        return axios.get(`/tournaments/users/${username}`)
    },
    getUsersInTourney(tourneyID) {
        return axios.get(`/tournaments/${tourneyID}/users/`)
    },
    getHostUsernameByTourneyID(tourneyID){
        return axios.get(`/tournaments/${tourneyID}/username`)
    }

   

}