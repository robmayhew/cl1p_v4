from django.shortcuts import render
from .models import RMPathKey
from .forms import TextContentForm
from .view_helper import save_text_content_for_path
from .view_helper import fetch_text_content_for_path
from .control.view_access import is_allowed_to_write
from .control.view_access import is_allowed_to_read
from .control.view_access import is_there_any_content_to_view
# Create your views here.
def wildcard_view(request, path):

    if request.method == 'POST':
        if is_allowed_to_write(path):
            form = TextContentForm(request.POST)
            if form.is_valid():
                my_text = form.cleaned_data['my_text']
                save_text_content_for_path(path, my_text)
                return render(request, 'cl1papp/cl1p_saved.html', {'path': path})
        else:
            return render(request, 'cl1papp/cl1p_not_allowed.html', {'path': path})
    else:
        if is_allowed_to_read(path):
            if is_there_any_content_to_view:
                content = fetch_text_content_for_path(path)
                form = TextContentForm()
                form.fields['my_text'].initial = content
                return render(request, 'cl1papp/cl1p_found.html', {'path': path, 'form': form})
            else:
                form = TextContentForm()
                return render(request, 'cl1papp/cl1p_not_found.html', {'path': path, 'form': form})
    return render(request, 'cl1papp/cl1p_found.html', {'path': path})



def get_cl1p_instance_by_path(path):
    try:
        return RMPathKey.objects.get(path=path)
    except RMPathKey.DoesNotExist:
        return None
    

def home(request):
    return render(request, 'home.html')