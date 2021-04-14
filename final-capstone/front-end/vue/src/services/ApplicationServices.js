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
    joinTourney(tourneyID, username, status){
        return axios.post(`/tournaments/${tourneyID}/${username}/${status}`)
    },
    leaveTourney(tourneyID, username){
        return axios.delete(`/tournaments/${tourneyID}/users/${username}`)
    },
    changeUserTourneyStatus(tourneyID, username, status){
        return axios.put(`/status/${tourneyID}/${username}/${status}`)
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
    },
    getMatchesInTourney(tourneyID){
        return axios.get(`/tourneys/matches/${tourneyID}`)
    },
    createMatch(match, tourneyID) {
        return axios.post(`/matches/tourneyID/${tourneyID}`, match)
    },
    addMatchToTourney(tourneyID, matchID) {
        return axios.post(`/tournaments/${tourneyID}/matches/${matchID}`)
    },
    addUserToMatch(matchID, username) {
        return axios.post(`/matches/${matchID}/${username}`)
    },
    getUserMatchLink(tourneyID) {
        return axios.get(`/user-matches/${tourneyID}`)
    },
    updateWinStatus(winStatus, userID, matchID) {
        return axios.put(`/usersmatches/${winStatus}/${userID}/${matchID}`)
    },
    tournamentsByUserStatus(username, status){
        return axios.get(`tournaments/user/${username}/status/${status}`)
    }
}