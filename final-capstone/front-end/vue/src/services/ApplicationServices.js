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
    }
}