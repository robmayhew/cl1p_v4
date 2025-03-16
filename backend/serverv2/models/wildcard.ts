import { InferSchemaType,Schema,model,Types } from 'mongoose';

const wildcardSchema = new Schema(
    {
        uri:  { type: String, required: true },
        content : { type: String, required: true },
      },
      {
        timestamps: { createdAt: 'created', updatedAt: 'lastAccess' },
      });

type WildcardType = InferSchemaType<typeof wildcardSchema>  & { _id: Types.ObjectId };
const WildcardModel =  model<WildcardType>("Wildcard",wildcardSchema);

export {WildcardType, WildcardModel};