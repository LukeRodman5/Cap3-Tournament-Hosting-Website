<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link> | 
      <router-link v-bind:to="{ name: 'add-tournament' }">Create a Tournament</router-link> | 
      <router-link v-bind:to="{ name: 'browse'}">Browse</router-link> | 
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-else>Login</router-link>
    </div>
    <router-view />
  </div>
</template>
<style>
html {
  background-color: rgb(185, 185, 251);
}
#nav {
  text-align: center;
  /* code added for testing during early phases, site looks okay for now, will keep just in case for now - 04/08/21
  background-color: rgb(185, 185, 251);
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas:
  "home home home"
  "nav tournaments tournaments"
  "footer footer footer";
  grid-gap: 10 px;
  height: 100vh; */
}
</style>
