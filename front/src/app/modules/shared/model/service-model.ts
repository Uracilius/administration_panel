export class ServiceModel{
    id: number;
    code: string;
    name: string;
    url: string;
    description: string;

    constructor(id: number, code: string, name: string, url: string, description: string){
        this.id = id;
        this.code = code;
        this.name = name;
        this.url = url;
        this.description = description;
    }
}