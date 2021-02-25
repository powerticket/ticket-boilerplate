from django.urls import path
from drf_yasg import openapi
from drf_yasg.views import get_schema_view
from rest_framework import permissions
from rest_framework.generics import ListCreateAPIView

from . import views

app_name = 'todos'
urlpatterns = [
   path('', views.TodoListCreateAPIView.as_view(), name='todo_lc'),
   path('<int:pk>/', views.TodoRetrieveUpdateDestroyAPIView.as_view(), name='todo_rud'),
]
