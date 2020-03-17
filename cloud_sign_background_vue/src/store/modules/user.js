export default {
  namespaced: true,
  state: {
    id: 0,
    name: '',
    userImgSrc: ''
  },
  mutations: {
    updateId (state, id) {
      state.id = id
    },
    updateName (state, name) {
      state.name = name
    },
    updateImgSrc(state,ImgSrc){
      state.userImgSrc = ImgSrc
    }
  }
}
