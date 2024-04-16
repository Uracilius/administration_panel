import {ServiceModel} from "./service-model";

export class MultiSelectModel{
  valueList : ServiceModel[];
  selectedList : ServiceModel[];

  constructor(valueList: ServiceModel[], selectedList: ServiceModel[]) {
    this.valueList = valueList;
    this.selectedList = selectedList;
  }
}
