<template>
  <div>
    <h1>Board</h1>
    <ul v-if="board.length !== 0">
      <li v-for="({username, content}, index) in board" :key="index">{{ username }}: {{ content }}</li>
    </ul>
    <input type="text" v-model="username" placeholder="유저" @keydown.enter="postTodo">
    <input type="text" v-model="content" placeholder="댓글" @keydown.enter="postTodo">
    <button @click="postTodo">쓰기</button>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'

const API_URL = process.env.VUE_APP_API_URL || 'http://localhost:8080'

export default Vue.extend({
  data: () => ({
    username: '',
    content: '',
    board: []
  }),

  created () {
    this.getTodo()
  },

  methods: {
    getTodo () {
      fetch(`${API_URL}/comment/`)
        .then(response => response.json())
        .then(json => {
          this.board = json
        })
    },

    postTodo () {
      if (this.username && this.content) {
        const formData = new FormData()
        formData.append('username', this.username)
        formData.append('content', this.content)
        this.username = ''
        this.content = ''
        fetch(`${API_URL}/comment/`, {
          method: 'POST',
          body: formData
        })
          .then(() => this.getTodo())
      } else {
        alert('빈칸을 채워주세요.')
      }
    }
  }
})
</script>
