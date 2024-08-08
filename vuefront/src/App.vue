<template>
  <div id="app">
    <Header
      v-show="!$route.meta.hideLayout"
      @toggle-sidebar="toggleSidebar"
      :class="{'main-header': true, 'sidebar-open': isSidebarVisible}"
    />
    <i 
      v-show="!$route.meta.hideLayout"
      class="bi bi-list fixed-menu-icon"
      @click="toggleSidebar">
    </i>
    <Sidebar 
      v-show="!$route.meta.hideLayout"
      :isVisible="isSidebarVisible" />
    <main :class="{'main-content': true, 'sidebar-open': isSidebarVisible}">
      <router-view :isSidebarVisible="isSidebarVisible" :setSidebarVisibility="setSidebarVisibility"/>
    </main>
    <i class="bi bi-caret-up scroll-to-top" @click="scrollToTop">
      <p style="display:inline; font-size:20px; font-style: normal;">TOP</p>
    </i>
    <Footer/>
  </div>
</template>

<script>
import Footer from './components/Footer.vue'
import Header from './components/Header.vue'
import Sidebar from './components/Sidebar.vue'

export default {
  components: { Header, Footer, Sidebar },
  data() {
    return {
      isSidebarVisible: !this.$route.meta.hideLayout,
    };
  },
  watch: {
    '$route'(to, from) {
      this.isSidebarVisible = !to.meta.hideLayout;
    }
  },
  methods: {
    toggleSidebar() {
      this.isSidebarVisible = !this.isSidebarVisible;
    },
    setSidebarVisibility(isVisible) {
      this.isSidebarVisible = isVisible;
    },
    scrollToTop() {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    },
  },
}
</script>

<style>
html, body {
  overflow-x: hidden;
}
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;
  min-height: 100vh;
  background: #0c1b49 no-repeat center center fixed;
  background-size: cover;
}

.header-visible, .sidebar-visible, .footer-visible {
  position: relative; /* Ensure lower z-index than .landing */
  z-index: 10;
}

.landing-hidden {
  margin-top: 0; /* Adjust layout if necessary */
}
</style>
