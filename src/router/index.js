import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/layout'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/login",
    children: [
      {
        path: '/home',
        name: 'Home',
        component: () => import('../views/Home')
      },
      {
        path: '/userManage',
        name: 'user',
        component: () => import('../views/UserManage')
      },
      {
        path: '/treeHole',
        name: 'TreeHole',
        component: () => import('../views/TreeHole')
      },
      {
        path: '/search',
        name: 'search_web',
        component: () => import('../views/Search')
      },
      {
        path: '/overview',
        name: 'Overview',
        component: () => import('../views/Overview')
      },
      {
        path: '/gwas',
        name: 'Gwas',
        component: () => import('../views/Gwas')
      },
      {
        path: '/eqtl',
        name: 'eQTL',
        component: () => import('../views/eQTL')
      },
      {
        path: '/meqtl',
        name: 'meQTL',
        component: () => import('../views/meQTL')
      },
      {
        path: '/emqtl',
        name: 'emQTL',
        component: () => import('../views/emQTL')
      },
      {
        path: '/ewas',
        name: 'Ewas',
        component: () => import('../views/Ewas')
      },

      {
        path: '/test1',
        name: 'Test1',
        component: () => import('../views/Test1')
      },
      {
        path: '/test2',
        name: 'Test2',
        component: () => import('../views/Test2')
      },

    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/register')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
