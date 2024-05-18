from django import forms

class TextContentForm(forms.Form):
    my_text = forms.CharField(widget=forms.Textarea,label='Enter your text here', required=False)