import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-relation-form',
  templateUrl: './relation-form.component.html',
  styleUrl: './relation-form.component.css'
})
export class RelationFormComponent {
  title: string = 'Default Form Name';
  isEdit: boolean = false;
  customForm: FormGroup = new FormGroup({});

  onSubmit(){
    console.log('Form submitted')
  }
}
