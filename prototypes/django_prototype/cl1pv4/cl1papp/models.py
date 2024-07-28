from django.db import models


class RMPathKey(models.Model):
    id = models.AutoField(primary_key=True)
    path = models.CharField(max_length=255, unique=True)
    
    def __str__(self):
        return self.path
    
class RMPathContent(models.Model):
    id = models.AutoField(primary_key=True)
    key = models.ForeignKey(RMPathKey, on_delete=models.CASCADE)
    content = models.TextField()
    
    def __str__(self):
        return self.key.path