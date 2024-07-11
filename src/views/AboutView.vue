<template>
  <div class="about">
    <Header></Header>

    <div class="about-banner">
      <h1>About Our Financial Platform</h1>
      <p>Empowering Your Financial Future Since 2010</p>
    </div>

    <div class="content">
      <section class="about-section">
        <h2 class="public-title"><span>Our Mission</span></h2>
        <p class="about-text">
          At our core, we believe in democratizing finance. Our mission is to provide innovative, secure, and user-friendly financial solutions that empower individuals to take control of their financial future. We strive to break down barriers in the financial world, making investment opportunities accessible to everyone, regardless of their background or initial capital.
        </p>
      </section>

      <section class="about-section">
        <h2 class="public-title"><span>Our Journey</span></h2>
        <div class="timeline">
          <div class="timeline-item">
            <div class="year">2010</div>
            <div class="event">Founded with a vision to revolutionize online investing</div>
          </div>
          <div class="timeline-item">
            <div class="year">2015</div>
            <div class="event">Reached 1 million registered users</div>
          </div>
          <div class="timeline-item">
            <div class="year">2018</div>
            <div class="event">Launched innovative AI-driven risk assessment system</div>
          </div>
          <div class="timeline-item">
            <div class="year">2022</div>
            <div class="event">Expanded services to 50 countries worldwide</div>
          </div>
        </div>
      </section>

      <section class="about-section">
        <h2 class="public-title"><span>Why Choose Us</span></h2>
        <ul class="about-features">
          <li>
            <img src="@/assets/image/2-icon4.png" alt="Reliability">
            <h3>Unmatched Reliability</h3>
            <p>With $100 million in registered capital and $50 million invested in cutting-edge technology, we offer a rock-solid foundation for your investments.</p>
          </li>
          <li>
            <img src="@/assets/image/2-icon5.png" alt="Security">
            <h3>Bank-Grade Security</h3>
            <p>Our platform employs 256-bit encryption, multi-factor authentication, and undergoes regular security audits to ensure your data and funds are always protected.</p>
          </li>
          <li>
            <img src="@/assets/image/2-icon3.png" alt="Convenience">
            <h3>Seamless User Experience</h3>
            <p>Intuitive interface, real-time market data, and instant transactions make managing your portfolio a breeze.</p>
          </li>
        </ul>
      </section>

      <section class="about-section">
        <h2 class="public-title"><span>Our Team</span></h2>
        <div class="about-team">
          <p>Our diverse team brings together expertise from finance, technology, and risk management. Led by industry veterans, we're committed to pushing the boundaries of what's possible in online finance.</p>
          <div class="team-members">
            <div class="team-member">
              <h4>Jane Doe</h4>
              <p>CEO & Founder</p>
            </div>
            <div class="team-member">
              <h4>John Smith</h4>
              <p>Chief Technology Officer</p>
            </div>
            <div class="team-member">
              <h4>Emily Brown</h4>
              <p>Chief Financial Officer</p>
            </div>
          </div>
        </div>
      </section>

      <section class="about-section">
        <h2 class="public-title"><span>Contact Us</span></h2>
        <div class="about-contact">
          <div class="contact-info">
            <p><i class="icon-email"></i> support@financialplatform.com</p>
            <p><i class="icon-phone"></i> +1 (123) 456-7890</p>
            <p><i class="icon-location"></i> 123 Finance Street, New York, NY 10001, USA</p>
          </div>
          <div class="social-media">
            <a href="#" class="social-icon"><i class="icon-facebook"></i></a>
            <a href="#" class="social-icon"><i class="icon-twitter"></i></a>
            <a href="#" class="social-icon"><i class="icon-linkedin"></i></a>
          </div>
        </div>
      </section>
    </div>

    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/common/Header"
import Footer from "@/components/common/Footer"

export default {
  name: "AboutView",
  components: {
    Header,
    Footer
  }
}
</script>

<style scoped>
.about-banner {
  background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('@/assets/image/about-banner.png');
  background-position:  top center;
  background-size: 100% 180%;
  color: white;
  padding: 100px 0;
  text-align: center;
}

.about-banner h1 {
  font-size: 3em;
  margin-bottom: 20px;
}

.about-banner p {
  font-size: 1.2em;
}

.about-section {
  margin: 60px 0;
}

.about-text {
  text-align: center;
  font-size: 1.1em;
  line-height: 1.8;
  max-width: 800px;
  margin: 20px auto;
}

.timeline {
  position: relative;
  max-width: 800px;
  margin: 40px auto;
  padding: 40px 0;
}

.timeline::after {
  content: '';
  position: absolute;
  width: 6px;
  background-color: #3498db;
  top: 0;
  bottom: 0;
  left: 50%;
  margin-left: -3px;
}

.timeline-item {
  padding: 10px 40px;
  position: relative;
  background-color: inherit;
  width: 50%;
}

.timeline-item::after {
  content: '';
  position: absolute;
  width: 25px;
  height: 25px;
  right: -17px;
  background-color: white;
  border: 4px solid #3498db;
  top: 15px;
  border-radius: 50%;
  z-index: 1;
}

.timeline-item:nth-child(odd) {
  left: 0;
}

.timeline-item:nth-child(even) {
  left: 50%;
}

.timeline-item:nth-child(even)::after {
  left: -16px;
}

.year {
  font-weight: bold;
  color: #3498db;
}

.about-features {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  margin: 40px 0;
}

.about-features li {
  text-align: center;
  width: 30%;
  margin-bottom: 30px;
}

.about-features img {
  width: 80px;
}

.about-team, .about-contact {
  text-align: center;
  margin: 20px 0;
}

.about-contact p {
  margin: 10px 0;
}
</style>
