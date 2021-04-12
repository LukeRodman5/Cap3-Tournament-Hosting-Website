<template>
  <div id="app">
    <h1 class ="header">Tournament Manager</h1>
    <p class ="header">Your home for hosting, finding, and playing tournaments of all kinds</p>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'add-tournament' }">Create a Tournament</router-link>
      <router-link v-bind:to="{ name: 'my-tournaments', params:{username: $store.state.user.username} }">Show My Tournaments</router-link>
      <router-link v-bind:to="{ name: 'about-us'}">About Us</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-else>Login</router-link>
    </div>
    <router-view />
 
  </div>
</template>
<style>
html {
  background-color: rgb(245, 244, 244);
}
#nav {
  display: flex;
  justify-content: space-evenly;
  background-color: rgb(0, 0, 70);
  box-shadow: 0px 2px 6px black;
  font-family:sans-serif;

}

#nav > a {
  font-family:'Arial Narrow Bold', sans-serif;
  padding: 10px 40px 10px 40px;
  text-decoration: none;
  color: rgba(255, 255, 255, 0.726);
}
#nav > a:hover {
  color: rgb(255, 255, 255);
  text-shadow: rgb(116, 101, 255) 0px 1px 4px;
  
}


a.router-link-exact-active {
  text-decoration: underline;
}
h1{
  text-align: center;
}
p{
  text-align: center;
}
h2{
  text-align: left;
}


</style>