from django.db import models


# Create your models here.
class Todo(models.Model):
    check = models.BooleanField(default=False)
    title = models.CharField(max_length=200)
    content = models.TextField(default='', blank=True)
    created_at = models.DateTimeField(auto_now_add=True)
    def __str__(self):
        return self.title
