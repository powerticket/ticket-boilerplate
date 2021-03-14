from django.test import TestCase
from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase
from todos.models import Todo

# Create your tests here.

class TodoTests(APITestCase):
    def test_todo_create(self):
        """
        POST /todos/
        """
        # given
        url = reverse('todos:todo_lc')
        data = {'title': 'test_title'}

        # when
        response = self.client.post(url, data, format='json')

        # then
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)
        self.assertEqual(Todo.objects.count(), 1)
        self.assertEqual(Todo.objects.get().title, 'test_title')


    def test_todo_list(self):
        """
        GET /todos/
        """
        # given
        url = reverse('todos:todo_lc')
        data1 = {'title': 'test_title1'}
        data2 = {'title': 'test_title2'}

        # when
        response = self.client.get(url)
        count = response.data.get('count')
        self.client.post(url, data1, format='json')
        self.client.post(url, data2, format='json')

        # then
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(Todo.objects.count(), count + 2)
