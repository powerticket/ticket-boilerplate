from .serializers import TodoListSerializer
from .models import Todo
from rest_framework import generics, status
from rest_framework.response import Response
from rest_framework import permissions


class TodoListCreateAPIView(generics.ListCreateAPIView):
    """Todo List/Create

    Args:
        generics ([type]): [description]
    """
    queryset = Todo.objects.order_by('-pk')
    serializer_class = TodoListSerializer
    # permission_classes = [permissions.IsAuthenticatedOrReadOnly]


class TodoRetrieveUpdateDestroyAPIView(generics.RetrieveUpdateDestroyAPIView):
    """Todo Retrieve/Update/Destroy

    Args:
        generics ([type]): [description]
    """
    queryset = Todo.objects.all()
    serializer_class = TodoListSerializer
