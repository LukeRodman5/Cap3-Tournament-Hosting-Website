<template>
<div>
<div class="about-section">
  <h1>About Us</h1>
  <!-- <p>Some text about who we are and what we do.</p>
  <p>Resize the browser window to see that this page is responsive by the way.</p> -->
</div>

<h2 style="text-align:center">Our Team</h2>
<div class ="col-container">
<div class="row">
  <div class="column">
    <div class="card">
      <!-- <img src="/w3images/team1.jpg" alt="Jane" style="width:100%"> -->
      <div class="container">
        <h2>Luke Rodman</h2>
        <p class="title">Java Tech Elevator Student</p>
        <p>luke.rodman5@gmail.com</p>
        <p><button class="button">Contact</button></p>
        <p class ="desc">Exceptionally focused and reliable full-stack Java developer with leadership in project management and strategic analysis. Seeking a challenging and innovative opportunity where I can support the growth & profitability of an organization that provides challenge, encourages advancement, & rewards achievement.
Proven experience developing and implementing operational controls to reduce annual spending by 30% year over year. Eager to develop my working experience in Java development, database programming, JavaScript, CSS, and SQL, while contributing my knowledge of creating custom websites using restful API's. Technology is a lifelong passion of mine. I look forward to applying my experiences in strategic database analysis and goal-oriented application development.</p>
      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <!-- <img src="/w3images/team2.jpg" alt="Mike" style="width:100%"> -->
      <div class="container">
        <h2>Anne Hoback</h2>
        <p class="title">Java Tech Elevator Student</p>
        <p>ahoback</p>
        <p><button class="button">Contact</button></p>
        <p class ="desc">In my banking career, I built a reputation as the person to go to when you needed someone to take a problem apart, find the root cause, and strategize a solution. A former manager once told me that when she gave me a problem to solve it was like watching a pit bull with a pot roast.
I'm an insatiable learner with an epic sense of responsibility. I believe that people have tremendous potential for positive creation that so often gets drowned in our day-to-day tasks. That's what draws me to technology -- the potential to create solutions that free people to put their energy where their passion is. I'm looking to join a team that's passionate about their work, and never loses sight of the people their work will touch. </p>
      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <!-- <img src="/w3images/team3.jpg" alt="John" style="width:100%"> -->
      <div class="container">
        <h2>Louis Dinh</h2>
        <p class="title">Java Tech Elevator Student</p>
        <p>louisd895@gmail.com</p>
        <p><button class="button">Contact</button></p>
        <p class ="desc">A University of Washington graduate, I had the pleasure in building my teamwork and practical skills with many different people. With my persistence in problem-solving, I love to analyze issues and try whatever I can to assist in getting the issue resolved, whether it be hardware or software related. </p>

      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <!-- <img src="/w3images/team3.jpg" alt="John" style="width:100%"> -->
      <div class="container">
        <h2>Michael Phanhthalath</h2>
        <p class="title">Java Tech Elevator Student</p>
        <p>mphanhthalath@gmail.com</p>
        <p><button class="button">Contact</button></p>
        <p class ="desc">I'm a student at Tech Elevator, starting to look for roles in the tech industry. As a former music production student and US Army air defender, I'm deeply drawn to technology and the creativity that arises in collaboration. From working with teams as a soldier to collaborating in music, I'd like to continue in working together with passionate and innovative thinkers to create tomorrow's up-and-coming technologies! </p>

      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <!-- <img src="/w3images/team3.jpg" alt="John" style="width:100%"> -->
      <div class="container">
        <h2>Joel Perez</h2>
        <p class="title">Java Tech Elevator Student</p>
        <p>hjoelperez216@gmail.com</p>
        <p><button class="button">Contact</button></p>
        <p>I’m currently a Java student at Tech Elevator. I graduated from Baldwin-Wallace University with a bachelor’s degree in Marketing and Math. Fun fact about myself, I used to be a Latin Dance instructor in college. This was important as I got to know new people and helped break them out of their shells. While also looking good on the dance floor I’ve been at Key for almost 10 years, and recently I focused on setting up file transfer networks between clients and the bank. I provided customer service to our clients as I met any and all needs when possible. I love the feeling I get from FINALLY solving an hours long problem, or the feeling of pride I get when I know I am helping someone else understand something they didn’t before. Nowadays I'm just keeping the dream alive of eventually becoming a game developer. </p>

      </div>
    </div>
  </div>
</div>
</div>
</div>
</template>
 
 <script>
export default {
  
}
</script>
 
 <style>
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}

html {
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: inherit;
}

.column {
  float: left;
  width: 20%;
  margin-bottom: 16px;
  padding: 0 8px;
  display:table-cell;
  


}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  margin: 8px;
    height: 100vh;
  
}

.about-section {
  padding: 50px;
  text-align: center;
  background-color: #474e5d;
  color: white;
  
}

.container {
  padding: 0 16px;
  display:table;
  width: 100%
  
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
}

.button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 4px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 30%;
  border-radius: 20px;

}

.button:hover {
  background-color: #555;
}

 @media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  } 
.column {
  display:table-cell;
}
}

 
 </style>