from django.shortcuts import get_object_or_404
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from .models import Todo
from .serializers import TodoDetailSerializer, TodoListSerializer


@api_view(['GET', 'POST'])
def todo_list(request):
    if request.method == 'GET':
        todos = Todo.objects.order_by('-pk')
        serializer = TodoListSerializer(todos, many=True)
        return Response(serializer.data)
    elif request.method == 'POST':
        serializer = TodoListSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)


@api_view(['GET', 'DELETE', 'PUT'])
def todo_detail(request, todo_pk):
    print('this is detail')
    if request.method == 'GET':
        todo = get_object_or_404(Todo, pk=todo_pk)
        serializer = TodoDetailSerializer(todo)
        return Response(serializer.data)
    elif request.method == 'PUT':
        print('this is PUT!!!')
        todo = get_object_or_404(Todo, pk=todo_pk)
        print(request.data)
        serializer = TodoDetailSerializer(instance=todo, data=request.data)
        print(serializer)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
    elif request.method == 'DELETE':
        print('this is delete')
        todo = get_object_or_404(Todo, pk=todo_pk)
        todo.delete()
        return Response({ 'id': todo_pk }, status=status.HTTP_204_NO_CONTENT)
