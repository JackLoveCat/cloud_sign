<template>
  <div
    class="site-wrapper"
    :class="{ 'site-sidebar--fold': sidebarFold }"
    v-loading.fullscreen.lock="loading"
    element-loading-text="拼命加载中"
  >
    <template v-if="!loading">
      <main-navbar />
      <main-sidebar />
      <div
        class="site-content__wrapper"
        :style="{ 'min-height': documentClientHeight + 'px' }"
      >
        <main-content />
      </div>
    </template>
  </div>
</template>

<script>
import MainNavbar from './main-navbar'
import MainSidebar from './main-sidebar'
import MainContent from './main-content'
export default {
  data () {
    return {
      loading: true
    }
  },
  components: {
    MainNavbar,
    MainSidebar,
    MainContent
  },
  computed: {
    documentClientHeight: {
      get () {
        return this.$store.state.common.documentClientHeight
      },
      set (val) {
        this.$store.commit('common/updateDocumentClientHeight', val)
      }
    },
    sidebarFold: {
      get () {
        return this.$store.state.common.sidebarFold
      }
    },
    userId: {
      get () {
        return this.$store.state.user.id
      },
      set (val) {
        this.$store.commit('user/updateId', val)
      }
    },
    userName: {
      get () {
        return this.$store.state.user.name
      },
      set (val) {
        this.$store.commit('user/updateName', val)
      }
    },
    userImgSrc: {
      get () {
        return this.$store.state.user.userImgSrc
      },
      set (val) {
        this.$store.commit('user/updateImgSrc', val)
      }
    }
  },
  created () {
    this.getUserInfo()
  },
  mounted () {
    this.resetDocumentClientHeight()
  },
  methods: {
    // // 重置窗口可视高度
    resetDocumentClientHeight () {
      this.documentClientHeight = 1500
      window.onresize = () => {
        this.documentClientHeight = document.documentElement['clientHeight']
      }
    },
    // 获取当前用户信息
    getUserInfo () {
      this.loading = false
      this.userImgSrc = './static/img/avatar.03cb40b.png'
      this.userName = ''
/*      this.$http({
        url: this.$http.adornUrl('role/getRole.do'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({ data }) => {
        if (data && data.status === 200) {
          this.loading = false
          this.userName = data.role.name
          this.userId = data.role.username
          this.userImgSrc = data.role.userImgSrc
        }
      })*/

/*      this.$http({
        url: this.$http.adornUrl('/getInfo'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({ data }) => {
        if (data && data.code === 200) {
          this.loading = false
          this.userName = data.user.nickName
          this.userId = data.user.userId
          this.userImgSrc = data.user.remark
        }
      })*/
    }
  }
}
</script>
