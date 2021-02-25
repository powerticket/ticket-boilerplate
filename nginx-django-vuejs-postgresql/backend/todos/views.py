from .serializers import TodoListSerializer
from .models import Todo
from rest_framework import generics, status
from rest_framework.response import Response
from rest_framework.permissions import IsAdminUser

class TodoListCreateAPIView(generics.ListCreateAPIView):
    queryset = Todo.objects.order_by('-pk')
    serializer_class = TodoListSerializer
    # permission_classes = [IsAdminUser]


class TodoRetrieveUpdateDestroyAPIView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Todo.objects.all()
    serializer_class = TodoListSerializer
