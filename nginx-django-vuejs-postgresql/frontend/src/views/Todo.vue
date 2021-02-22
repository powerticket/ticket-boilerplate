<template>
  <div v-if="init" class="todo">
    <h1>Simple To-Do Vue App</h1>
    <ul>
      <li v-for="todo in todos" :key="todo.id">
        <span v-if="todo.check" @click="checkTodo(todo)">✅</span>
        <span v-else @click="checkTodo(todo)">⬜</span>
        <p
          class="todo-p"
          :style="{
              'text-decoration': !todo.check ? 'none' : 'line-through',
          }"
        >
          {{ todo.title }}
        </p>
        <span @click="deleteTodo(todo.id)">❌</span>
      </li>
    </ul>
    <input @keydown.enter="addTodo" v-model.trim="todoTitle" type="text" placeholder="To-Do" autofocus>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import axios from 'axios'

const API_SERVER_URL = process.env.VUE_APP_API_SERVER_URL || 'http://localhost:8000'

export default Vue.extend({
  data: (): Record<string, any> => ({
    init: false,
    todoTitle: '',
    todos: []
  }),
  methods: {
    async getTodos () {
      try {
        const response = await axios.get(`${API_SERVER_URL}/todos/`)
        this.todos = response.data
        return true
      } catch (error) {
        alert(error)
        return false
      }
      
    },
    deleteTodo (todoId: number) {
      axios.delete(`${API_SERVER_URL}/todos/${todoId}/`)
        .then(response => {
          console.log(response)
          this.todos = this.todos.filter((t: Record<string, any>) => t.id !== todoId)
        })
        .catch(error => alert(error))
    },
    checkTodo (todo: Record<string, any>) {
      console.log(todo)
      axios.put(`${API_SERVER_URL}/todos/${todo.id}/`, {
        ...todo,
        check: !todo.check,
      })
        .then(response => {
          this.todos = this.todos.map((t: Record<string, any>) => {
            if (t.id === todo.id) {
              t = {
                ...t,
                check: !t.check
              }
            }
            return t
          });
        })
        .catch(error => alert(error))
    },
    addTodo () {
      if (this.todoTitle) {
        const data = {
          title: this.todoTitle,
        }
        axios.post(`${API_SERVER_URL}/todos/`, data)
          .then(response => {
            this.todos.unshift(response.data)
            this.todoTitle = ''
          })
          .catch(error => alert(error))
      }
    },
    initialize () {
      this.init = this.getTodos()
    }
  },
  created () {
    console.log(process.env.VUE_APP_API_SERVER_URL)
    this.initialize()
  }
})
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 0 10px;
  display: flex;
  align-items: center;
  cursor: default;
}
span {
  margin: 0 1rem;
}
input {
  width: 50%;
}
.todo-p {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
</style>
