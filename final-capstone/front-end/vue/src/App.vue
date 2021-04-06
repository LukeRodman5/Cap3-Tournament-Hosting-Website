<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp; |&nbsp;
      <router-link v-bind:to="{ name: 'add-tournament' }">Create a Tournament</router-link>&nbsp; |&nbsp;
      <router-link v-bind:to="{ name: 'browse'}"> Browse </router-link>
      
    
    </div>
    <router-view />
  </div>
</template>
<style>
body{
  background-color: rgb(185, 185, 251);
  display: grid;
  /* grid-template-columns: 1fr 3fr 1fr; */
  text-align: center;
  /* grid-template-areas:
  "home home home"
  "nav tournaments tournaments"
  "footer footer footer"; */
  grid-gap: 10 px;
  height: 100vh;
}
.nav{ 
  display: flex;

}
.home{
  background-color: darkmagenta;
}
</style>
