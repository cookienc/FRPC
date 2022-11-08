import { createRouter, createWebHistory} from "vue-router";
import HomeView from "../views/HomeView.vue";

const BarGraph = () => {
  return import(/* webpackChunkName: "barGraph" */ "../views/BarGraph.vue");
};

const LineGraph = () => {
  return import(/* webpackChunkName: "lineGraph" */ "../views/LineGraph.vue");
};

const LineFlexGraph = () => {
  return import(/* webpackChunkName: "lineGraph" */ "../views/LineFlexGraph.vue");
};

const List = () => {
  return import(/* webpackChunkName: "list" */ "../views/SensingList.vue");
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/bar-graph",
    name: "barGraph",
    component: BarGraph,
  },
  {
    path: "/line-graph",
    name: "lineGraph",
    component: LineGraph,
  },
  {
    path: "/line-graph/flex",
    name: "lineFlexGraph",
    component: LineFlexGraph,
  },
  {
    path: "/list",
    name: "list",
    component: List,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
