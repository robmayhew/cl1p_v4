from django.shortcuts import render
from .models import RMPathKey

# Create your views here.
def wildcard_view(request, path):
    rm_path = get_cl1p_instance_by_path(path)
    if rm_path is None:
        rm_path = RMPathKey()
        rm_path.path = path
        rm_path.save()
        return render(request, 'cl1papp/cl1p_not_found.html', {'path': path})
    else:
        rm_path.delete()
        return render(request, 'cl1papp/cl1p_found.html', {'path': path})



def get_cl1p_instance_by_path(path):
    try:
        return RMPathKey.objects.get(path=path)
    except RMPathKey.DoesNotExist:
        return None