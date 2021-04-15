<template>
  <div id="app">
    <h1 class ="header">TELeague<i class="fas fa-table-tennis"></i></h1>
    <p class ="header">Your home for hosting, finding, and playing tournaments of all kinds</p>
    <p class ="user"> Hello {{$store.state.user.username}}! 
    <i class="fas fa-user"></i>      
    </p>
    
    

    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'add-tournament' }">Create a Tournament</router-link>
      <router-link v-bind:to="{ name: 'my-tournaments', params:{username: $store.state.user.username} }">Show My Tournaments</router-link>
      <router-link v-bind:to="{ name: 'about-us'}">About Us</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-else>Login</router-link>
    </div>
    <router-view />
  <footer> follow us |
    <i class="fab fa-facebook"></i> |
    <i class="fab fa-twitter"></i> |
    <i class="fab fa-reddit"></i> |
    contact 
    <i class="far fa-envelope"></i>

 </footer>
  </div>
  
</template>
<style>
#app{
  position: relative;
  min-height: 100vh;
  
}
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
  font-family: times, Times New Roman, times-roman, georgia, serif;
  padding: 10px 40px 10px 40px;
  text-decoration: none;
  color: rgba(255, 255, 255, 0.781);
  /* text-shadow: rgb(0, 0, 0) 0px 1px 4px; */
}
#nav > a:hover {
  color: rgb(255, 255, 255);
  text-shadow: rgb(0, 0, 0) 0px 1px 4px;
  
}


a.router-link-exact-active {
  text-decoration: underline;
}
h1{
  text-align: center;
  font-family: times, Times New Roman, times-roman, georgia, serif;
	color: #444;
	margin: 0;
	padding: 0px 0px 6px 0px;
	font-size: 51px;
	line-height: 44px;
	letter-spacing: -2px;
	font-weight: bold;
}
p{
  text-align: center;
  font-family: times, Times New Roman, times-roman, georgia, serif;
	color: #444;
}
.user{
  text-align: right;
  padding-right: 20px;
  font-family: times, Times New Roman, times-roman, georgia, serif;
	color: #444;
}
h2{
  text-align: left;
}
.user:hover{
  color:blue
}
div{
  font-family: times, Times New Roman, times-roman, georgia, serif;
	color: #444;
}
footer{
  margin-top: 5px;
  background-color: lightgray;
  padding-bottom: 10px;
  padding-top: 10px;
  align-items: center;
  text-align: center;
  margin-right: 40%;
  margin-left: 40%;
  padding-right: 12px;
  padding-left: 12px;
  border-radius: 25px;
  box-shadow: 0px 2px 2px rgb(112, 112, 112);
  position: absolute;
  bottom: 0;
 
}


</style>