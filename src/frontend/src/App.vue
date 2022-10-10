<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer" app>
      <v-list dense>
        <v-list-item router :to="{ name: 'home' }" exact>
          <v-list-item-action>
            <i class="fas fa-home"></i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
            router
            :to="{ name: 'lineGraph', query: { date: this.getDate() } }"
            exact
        >
          <v-list-item-action>
            <i class="fas fa-lineGraph"></i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>LineGraph</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item router :to="{ name: 'list', query: { date: this.getDate() } }" exact>
          <v-list-item-action>
            <i class="fas fa-list"></i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>List</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title color="indigo" dark fixed app
                       style="margin-right: 80px"
      >손가락 재활 경과 확인기</v-toolbar-title
      >
    </v-app-bar>
    <v-main>
      <router-view :key="$route.fullPath"></router-view>
    </v-main>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    drawer: null,
  }),

  created() {
    this.getDate();
  },

  methods: {
    test() {
      alert("클릭됨");
    },

    getDate() {
      var today = new Date();

      var year = today.getFullYear();
      var month = ("0" + (today.getMonth() + 1)).slice(-2);
      var day = ("0" + today.getDate()).slice(-2);
      var dateString = year + "-" + month + "-" + day;

      var hours = ("0" + today.getHours()).slice(-2);
      var minutes = ("0" + today.getMinutes()).slice(-2);
      var seconds = ("0" + today.getSeconds()).slice(-2);
      var timeString = dateString + " " + hours + ":" + minutes + ":" + seconds;
      today = timeString;
      return timeString;
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
