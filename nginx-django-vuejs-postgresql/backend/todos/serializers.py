from rest_framework import serializers

from .models import Todo


class TodoListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Todo
        # fields = ('id', 'title', 'check')
        fields = '__all__'


class TodoDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = Todo
        fields = '__all__'
