<template>
  <div id="app">
    <Header
      @toggle-sidebar="toggleSidebar"
      :class="{'main-header': true, 'sidebar-open': isSidebarVisible}"
    />
    <i class="bi bi-list fixed-menu-icon" @click="toggleSidebar"></i>
    <Sidebar :isVisible="isSidebarVisible" />
    <main :class="{'main-content': true, 'sidebar-open': isSidebarVisible}">
      <router-view :isSidebarVisible="isSidebarVisible" :setSidebarVisibility="setSidebarVisibility"/>
    </main>
    <i class="bi bi-caret-up scroll-to-top" @click="scrollToTop"><p style="display:inline; font-size:20px">TOP</p></i>
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
      isSidebarVisible: true,
    };
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
</style>
