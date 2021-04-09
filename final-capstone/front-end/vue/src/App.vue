<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'add-tournament' }">Create a Tournament</router-link>
      <router-link v-bind:to="{ name: 'my-tournaments' }">Show My Tournaments</router-link>
      <router-link v-bind:to="{ name: 'about-us'}">About Us</router-link>
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
  display: flex;
  justify-content: space-evenly;
}

#nav > a {
  border: 1px solid black;
  padding: 10px 40px 10px 40px;
  text-decoration: none;
  color: black;
}

a.router-link-exact-active {
  text-decoration: underline;
}

</style>