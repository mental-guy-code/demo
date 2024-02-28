import { createStore } from 'vuex'

export default createStore({
  state: {
    userData: null,
  },
  mutations: {
    setUserData(state, data){
      state.userData = data;
    },
    // 在储存组件 this.$store.commit('setUserData', { /* your data */ });
    // 在跳转到的目标组件中，可以通过 this.$store.state.userData 获取数据
    clearUserData(state) {
      state.userData = null;
    }
  },
  actions: {
    // clearUserData(context) {
    //   context.commit('clearUserData');
    // }
    //this.$store.dispatch('clearUserData'); 在需要清除的地方使用这行
  },
  modules: {
  }
})
